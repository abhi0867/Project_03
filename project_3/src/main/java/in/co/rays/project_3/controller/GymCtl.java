package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.GymDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.GymModelHibImp;
import in.co.rays.project_3.model.ModelFactory;

import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/GymCtl" })
public class GymCtl extends BaseCtl{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserCtl.class);

				
		

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("-------------validate started-------------");
		
		
		if (DataValidator.isNull(request.getParameter("GymName"))) {
			request.setAttribute("GymName", PropertyReader.getValue("error.require", "Gym Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("GymName"))) {
			request.setAttribute("GymName", "please enter correct Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("JoningDate"))) {
			request.setAttribute("JoningDate", PropertyReader.getValue("error.require", "JoningDate"));
			pass = false;
		}else if (!DataValidator.isDate(request.getParameter("JoningDate"))) {
			request.setAttribute("JoningDate", PropertyReader.getValue("error.date", "JoningDate Of Birth"));
			pass = false;
		}else if (!DataValidator.isAge(request.getParameter("JoningDate"))) {
			
			request.setAttribute("JoningDate", "Age Must be greater then 18 year");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("tranerName"))) {
			request.setAttribute("tranerName", PropertyReader.getValue("error.require", "traner Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("tranerName"))) {
			request.setAttribute("tranerName", "please enter correct Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time", PropertyReader.getValue("error.require", "time "));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("time"))) {
			request.setAttribute("time", "please enter correct Name");
			pass = false;
		}
		
		
		
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		GymDTO dto = new GymDTO();
		
       dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setGymName(DataUtility.getString(request.getParameter("GymName")));
		
		dto.setJoningDate(DataUtility.getDate(request.getParameter("JoningDate")));
		
		dto.setTranerName(DataUtility.getString(request.getParameter("tranerName")));

		dto.setTime(DataUtility.getString(request.getParameter("time")));

		
        
		populateBean(dto,request);
		
		
		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("UserCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		GymModelHibImp model = (GymModelHibImp) ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			GymDTO dto=null;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("-------------------------------------------------------------------------dopost run-------");
		// get model
		GymModelHibImp model = (GymModelHibImp) ModelFactory.getInstance().getUserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			GymDTO dto = (GymDTO) populateDTO(request);
              System.out.println(" in do post method jkjjkjk++++++++"+dto.getId());
			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {
					
					try {
						 model.add(dto);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);
				
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			GymDTO dto = (GymDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.GYM_LIST_VIEW, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.GYM_LIST_VIEW, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.GYM_VIEW, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}
	
	

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.GYM_VIEW;
	}

}
