package org.guille.parcialsoa.controller;

import org.guille.parcialsoa.dto.TelefonoRequestDto;
import org.guille.parcialsoa.dto.TelefonoResponseDto;
import org.guille.parcialsoa.service.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TelefonoController {

	@Autowired(required = true)
	TelefonoService telefonoService;
	
	/*
	 * El método expuesto recibe un Long con la siguiente URI de ejemplo:
	 * 
	 * "/id/1"
	 * 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/id/{id}", 
					method = RequestMethod.GET, 
					produces = "application/json")
	public ResponseEntity<?> obtenerUsuarioYSaldo(@PathVariable Long id) throws Exception{
		TelefonoResponseDto telefonoDto = new TelefonoResponseDto();
		
		telefonoDto = telefonoService.obtenerUsuarioSaldo(id);
		
		return new ResponseEntity(telefonoDto, HttpStatus.OK);		
	}
	
	/*
	 * El método expuesto recibe un Long con la siguiente URI de ejemplo:
	 * 
	 * "/numero/123456789"
	 * 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/numero/{numero}", 
					method = RequestMethod.GET, 
					produces = "application/json")
	public ResponseEntity<?> obtenerUsuarioByNumero(@PathVariable String numero) throws Exception{
		TelefonoResponseDto telefonoDto = new TelefonoResponseDto();
		
		telefonoDto = telefonoService.obtenerUsuarioByNumero(numero);
		
		return new ResponseEntity(telefonoDto, HttpStatus.OK);		
	}
	
	/*
	 * El método expuesto recibe un JSon(TelefonoRequestDto) con el siguiente formato de ejemplo:
	 * 
	 * {
  	 *  "numero": 351359,
  	 *  "monto": 100,
  	 *  "usuario_id": 1
	 * }
	 * 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/", 
					method = RequestMethod.POST, 
					produces = "application/json")
	public ResponseEntity<?> registrarTelefono(@RequestBody TelefonoRequestDto telefonoReqDto) throws Exception{
		TelefonoResponseDto telefonoResDto;
		
		telefonoResDto = telefonoService.registrarTelefono(telefonoReqDto);
		
		return new ResponseEntity(telefonoResDto,HttpStatus.CREATED);
	}
	
	/*
	 * El método expuesto recibe un JSon(TelefonoRequestDto) con el siguiente formato de ejemplo:
	 * 
	 * {
  	 *	"id_telefono": 3,
     *	"monto": 150
	 * }
	 * 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/", 
					method = RequestMethod.PUT, 
					produces = "application/json")
	public ResponseEntity<?> cargaCredito(@RequestBody TelefonoRequestDto telefonoReqDto) throws Exception{
		TelefonoResponseDto telefonoResDto;
		
		telefonoResDto = telefonoService.cargaCredito(telefonoReqDto);
		
		return new ResponseEntity(telefonoResDto,HttpStatus.OK);
	}
}
