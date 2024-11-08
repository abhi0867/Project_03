package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ClassDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;

public interface ClassModelInt {
	public long add(ClassDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(ClassDTO dto)throws ApplicationException;
	public void update(ClassDTO dto)throws ApplicationException,DuplicateRecordException;
	public ClassDTO findByPK(long pk)throws ApplicationException;
	public ClassDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(ClassDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(ClassDTO dto)throws ApplicationException;
	public boolean changePassword(long id,String newPassword,String oldPassword)throws ApplicationException, RecordNotFoundException;
	public ClassDTO authenticate(String login,String password)throws ApplicationException;
	public boolean forgetPassword(String login)throws ApplicationException, RecordNotFoundException;
	public boolean resetPassword(ClassDTO dto)throws ApplicationException,RecordNotFoundException;
	public long registerUser(ClassDTO dto)throws ApplicationException,DuplicateRecordException;
	public List getRoles(ClassDTO dto)throws ApplicationException;


	}



