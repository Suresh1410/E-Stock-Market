package com.company.estockmarketcompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.estockmarketcompany.service.CompanyService;
import com.estockmarket.company.dto.CompanyDto;
import com.estockmarket.utils.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Register a new Company",response=ResponseEntity.class)
	@ApiResponse(code=200,message="successful",response=ResponseEntity.class)
	@PostMapping("/register")
	public ResponseEntity<CompanyDto> companyRegister(@RequestBody CompanyDto company) {
		ResponseEntity response=null;
		CompanyDto companyDto=companyService.companyRegister(company);
		if(companyDto != null) {
			response=new ResponseEntity(companyDto,HttpStatus.OK);
		}		
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Get company details based on company code",response=ResponseEntity.class)
	@ApiResponse(code=200,message="successful",response=ResponseEntity.class)
	@GetMapping("/info/{companyCode}")
	public ResponseEntity<CompanyDto> getCompany(@PathVariable("companyCode") Integer companyCode) {
		ResponseEntity response=null;
		CompanyDto companyDto=companyService.getCompany(companyCode);
		if(companyDto.getCompanyCode() != null) {
			response=new ResponseEntity(companyDto,HttpStatus.OK);
		}
		else {
			companyDto.setErrorMessage(Constants.COMPANY_NOT_FOUND);
			response=new ResponseEntity(companyDto,HttpStatus.OK);
		}
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Get all company details",response=ResponseEntity.class)
	@ApiResponse(code=200,message="successful",response=ResponseEntity.class)
	@GetMapping("/getall")
	public ResponseEntity<List<CompanyDto>> getAllCompany() {
		ResponseEntity response=null;
		List<CompanyDto> companyDto=companyService.getAllCompany();
		if(companyDto != null) {
			response=new ResponseEntity(companyDto,HttpStatus.OK);
		}
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="Delete details based on company code",response=ResponseEntity.class)
	@ApiResponse(code=200,message="successful",response=ResponseEntity.class)
	@DeleteMapping("/delete/{companyCode}")
	public ResponseEntity<String> deleteCompany(@PathVariable("companyCode") Integer companyCode){
		 String result=companyService.deleteCompany(companyCode);
		 ResponseEntity response=new ResponseEntity(result,HttpStatus.OK);
		 return response;
	}
}
