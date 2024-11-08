package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.BusDTO;

import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;

public interface BusModelInt {	
	public long add(BusDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(BusDTO dto)throws ApplicationException;
	public void update(BusDTO dto)throws ApplicationException,DuplicateRecordException;
	public BusDTO findByPK(long pk)throws ApplicationException;
	public BusDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(BusDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(BusDTO dto)throws ApplicationException;
	public boolean changePassword(long id,String newPassword,String oldPassword)throws ApplicationException, RecordNotFoundException;
	public BusDTO authenticate(String login,String password)throws ApplicationException;
	public boolean forgetPassword(String login)throws ApplicationException, RecordNotFoundException;
	public boolean resetPassword(BusDTO dto)throws ApplicationException,RecordNotFoundException;
	public long registerUser(BusDTO dto)throws ApplicationException,DuplicateRecordException;
	public List getRoles(BusDTO dto)throws ApplicationException;


	}

	


