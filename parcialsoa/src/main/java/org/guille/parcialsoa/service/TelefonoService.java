package org.guille.parcialsoa.service;

import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.guille.parcialsoa.dao.IGenericDao;
import org.guille.parcialsoa.dao.ITelefonoDao;
import org.guille.parcialsoa.dao.TelefonoDao;
import org.guille.parcialsoa.dto.TelefonoRequestDto;
import org.guille.parcialsoa.dto.TelefonoResponseDto;
import org.guille.parcialsoa.dto.UsuarioDto;
import org.guille.parcialsoa.exception.BadRequestException;
import org.guille.parcialsoa.model.Movimiento;
import org.guille.parcialsoa.model.Telefono;
import org.guille.parcialsoa.model.Transaccion;
import org.guille.parcialsoa.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TelefonoService {
	
	@Autowired
	private IGenericDao<Telefono, Long> telefonoDao;
	
	@Autowired
	private IGenericDao<Usuario, Long> usuarioDao;
	
	@Autowired
	private IGenericDao<Transaccion, Long> transaccionDao;
	
	ITelefonoDao telefonoParticular;
	
	@PostConstruct
	public void init(){
		telefonoParticular = (TelefonoDao)telefonoDao;
	}
	
	public TelefonoResponseDto obtenerUsuarioSaldo(Long id) throws Exception{
		
		Telefono telefono = (Telefono)telefonoDao.load(id);
		
		if(telefono != null){
			UsuarioDto usuarioDto = new UsuarioDto();
			usuarioDto.setApellido(telefono.getUsuario().getApellido());
			usuarioDto.setNombre(telefono.getUsuario().getNombre());
			usuarioDto.setEmail(telefono.getUsuario().getEmail());
			usuarioDto.setIdUsuario(telefono.getUsuario().getId());
			
			TelefonoResponseDto telefonoDto = new TelefonoResponseDto();
			telefonoDto.setIdTelefono(telefono.getId());
			telefonoDto.setUsuario(usuarioDto);
			telefonoDto.setSaldo(telefono.getSaldo());
			telefonoDto.setNumero(telefono.getNumero());
			
			return telefonoDto;
		}else{
			throw new BadRequestException("3000", "El id del telefono provisto no se encuentra registrado.");
		}
	}
	
	public TelefonoResponseDto obtenerUsuarioByNumero(String numero) throws Exception{
		
		List<Telefono> telefonos = (List<Telefono>)telefonoParticular.obtenerUsuarioByNumero(numero);
		
		if(telefonos.size() == 1){
			Telefono telefono = telefonos.get(0);
			
			UsuarioDto usuarioDto = new UsuarioDto();
			usuarioDto.setApellido(telefono.getUsuario().getApellido());
			usuarioDto.setNombre(telefono.getUsuario().getNombre());
			usuarioDto.setEmail(telefono.getUsuario().getEmail());
			usuarioDto.setIdUsuario(telefono.getUsuario().getId());
			
			TelefonoResponseDto telefonoDto = new TelefonoResponseDto();
			telefonoDto.setIdTelefono(telefono.getId());
			telefonoDto.setUsuario(usuarioDto);
			telefonoDto.setSaldo(telefono.getSaldo());
			telefonoDto.setNumero(telefono.getNumero());
			
			return telefonoDto;
		}else{
			throw new BadRequestException("3000", "El id del telefono provisto no se encuentra registrado.");
		}
	}
	
	public TelefonoResponseDto registrarTelefono(TelefonoRequestDto telefonoReqDto) throws Exception{
		
		if(telefonoReqDto.getNumero() != null && telefonoReqDto.getMonto() != null && telefonoReqDto.getIdUsuario() != null){
			Telefono telefono = new Telefono();
			Usuario usuario = new Usuario();
			Transaccion transaccion = new Transaccion();
			
			//Se valida si el número de telefono ya se encuentra registrado
			List<Telefono> telefonos = (List<Telefono>)telefonoParticular.obtenerUsuarioByNumero(telefonoReqDto.getNumero());
			
			if(telefonos.size() == 1){
				throw new BadRequestException("3001", "El número de teléfono ya se encuentra registrado.");
			}
			
			//Obtengo el usuario desde la BD.
			usuario = usuarioDao.load(telefonoReqDto.getIdUsuario());
			
			//Seteo los valores del telefono
			telefono.setNumero(telefonoReqDto.getNumero());
			telefono.setSaldo(telefonoReqDto.getMonto());
			telefono.setUsuario(usuario);
			
			//Registro el telefono en la DB
			telefonoDao.saveOrUpdate(telefono);
			
			//Registro la transaccion inicial
			transaccion.setFechaTransaccion(GregorianCalendar.getInstance().getTime());
			transaccion.setMonto(telefonoReqDto.getMonto());
			transaccion.setMovimiento(Movimiento.CREDITO);
			transaccion.setTelefono(telefono);
			
			transaccionDao.saveOrUpdate(transaccion);
			
			//Se asignan a telefonoResponseDto los datos que serán devueltos.
			UsuarioDto usuarioDto = new UsuarioDto();
			usuarioDto.setApellido(usuario.getApellido());
			usuarioDto.setNombre(usuario.getNombre());
			usuarioDto.setEmail(usuario.getEmail());
			usuarioDto.setIdUsuario(usuario.getId());
			
			TelefonoResponseDto telefonoResDto = new TelefonoResponseDto();
			telefonoResDto.setIdTelefono(telefono.getId());
			telefonoResDto.setSaldo(telefono.getSaldo());
			telefonoResDto.setNumero(telefono.getNumero());
			telefonoResDto.setUsuario(usuarioDto);
			
			return telefonoResDto;
		}else{
			throw new BadRequestException("3002", "Los datos provistos están incompletos.");
		}
	}
	
	public TelefonoResponseDto cargaCredito(TelefonoRequestDto telefonoReqDto) throws Exception{
		
		if(telefonoReqDto.getIdTelefono() != null && telefonoReqDto.getMonto() != null){
			
			Telefono telefono;
			Transaccion transaccion = new Transaccion();
		
			//Obtengo los datos actuales del teléfono
			telefono = telefonoDao.load(telefonoReqDto.getIdTelefono());
			
			if(telefono != null){
				telefono.setSaldo(new Double(telefono.getSaldo().doubleValue() + telefonoReqDto.getMonto().doubleValue()));
				
				//registro el nuevo saldo
				telefonoDao.saveOrUpdate(telefono);
				
				//Registro la transaccion de carga de credito
				transaccion.setFechaTransaccion(GregorianCalendar.getInstance().getTime());
				transaccion.setMonto(telefonoReqDto.getMonto());
				transaccion.setMovimiento(Movimiento.CREDITO);
				transaccion.setTelefono(telefono);
				
				transaccionDao.saveOrUpdate(transaccion);
				
				//Seteo el nuevo saldo en el DTO para confirmar 
				TelefonoResponseDto telefonoResDto = new TelefonoResponseDto();
				telefonoResDto.setIdTelefono(telefono.getId());
				telefonoResDto.setNumero(telefono.getNumero());
				telefonoResDto.setSaldo(telefono.getSaldo());
				
				//Obtengo de la DB los datos del cliente
				Usuario usuario = usuarioDao.load(telefono.getUsuario().getId());
				UsuarioDto usuarioDto = new UsuarioDto();
				usuarioDto.setApellido(usuario.getApellido());
				usuarioDto.setNombre(usuario.getNombre());
				usuarioDto.setEmail(usuario.getEmail());
				usuarioDto.setIdUsuario(usuario.getId());
				
				//Asigno los datos del usuario a la instancia de telefonoDto.
				telefonoResDto.setUsuario(usuarioDto);
				
				return telefonoResDto;
			}else{
				throw new BadRequestException("3000", "El id del telefono provisto no se encuentra registrado.");
			}
		}else{
			throw new BadRequestException("3002", "Los datos provistos están incompletos.");
		}
	}
}
