package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.BusDTO;
import in.co.rays.project_3.dto.ProductDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.BusModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.ProductModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/BusCtl" })
public class BusCtl extends BaseCtl {
	
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("busNo"))) {
			request.setAttribute("busNo", PropertyReader.getValue("error.require", "busNo"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("busNo"))) {
			request.setAttribute("busNo", "busNo must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("busId"))) {
			request.setAttribute("busId", PropertyReader.getValue("error.require", "busId"));
			pass = false;
		} else if (!DataValidator.isDouble(request.getParameter("busId"))) {
			request.setAttribute("busId", "busId must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("km"))) {
			request.setAttribute("km", PropertyReader.getValue("error.require", "km"));
			pass = false;
		}
	
	
		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		BusDTO dto = new BusDTO();
		dto.setBusNo( DataUtility.getInt(request.getParameter("busNo")));
	    dto.setBusId(DataUtility.getDouble(request.getParameter("busId")));
	    System.out.println("km ============ > " + request.getParameter("km"));
		dto.setKm(DataUtility.getLong(request.getParameter("km")));
	    System.out.println("okayyyyyyy---"+dto.getKm());
		populateBean(dto, request);
		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));
		BusModelInt model = ModelFactory.getInstance().getBusModel();
		if (id > 0 || op != null) {
			BusDTO dto;
			try {
				dto = model.findByPK(id);
		
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				
				ServletUtility.handleException(e, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));

		BusModelInt model = ModelFactory.getInstance().getBusModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			BusDTO dto = (BusDTO) populateDTO(request);

			try {
				if (id > 0) {
					dto.setId(id);
					model.update(dto);
					ServletUtility.setDto(dto, request);

					ServletUtility.setSuccessMessage("Record Successfully Updated", request);

				} else {
					System.out.println("college add" + dto + "id...." + id);
					
					model.add(dto);
					ServletUtility.setSuccessMessage("Record Successfully Saved", request);
				}
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("ProductName Already Exists", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.BUS_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.BUS_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.BUS_VIEW;
	}

}
