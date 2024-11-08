package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.GymDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;

public interface GymModelInt {
	public long add(GymDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(GymDTO dto)throws ApplicationException;
	public void update(GymDTO dto)throws ApplicationException,DuplicateRecordException;
	public GymDTO findByPK(long pk)throws ApplicationException;
	public GymDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(GymDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(GymDTO dto)throws ApplicationException;
	public boolean changePassword(long id,String newPassword,String oldPassword)throws ApplicationException, RecordNotFoundException;
	public GymDTO authenticate(String login,String password)throws ApplicationException;
	public boolean forgetPassword(String login)throws ApplicationException, RecordNotFoundException;
	public boolean resetPassword(GymDTO dto)throws ApplicationException,RecordNotFoundException;
	public long registerUser(GymDTO dto)throws ApplicationException,DuplicateRecordException;
	public List getRoles(GymDTO dto)throws ApplicationException;



}
