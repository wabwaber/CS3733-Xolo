package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.AlternativeDAO;
import wpi.xojo.g2.project.http.AddAlternativeRequest;
import wpi.xojo.g2.project.http.AddAlternativeResponse;
import wpi.xojo.g2.project.model.Alternative;

public class AddAlternativeHandler implements RequestHandler<AddAlternativeRequest, AddAlternativeResponse> {
	LambdaLogger logger;
	
	boolean addAlternative(int ID, int choiceID, String desc) throws Exception {
		AlternativeDAO dao = new AlternativeDAO();
		Alternative exists = dao.getAlternative(ID);
		Alternative choice = new Alternative(ID,choiceID,desc);
		if (exists == null) {
			return dao.addAlternative(choice);
		} else {
			return false;
		}
	}
	
	@Override
	public AddAlternativeResponse handleRequest(AddAlternativeRequest req, Context context) {

		logger = context.getLogger();
		logger.log(req.toString());
		
		AddAlternativeResponse response;
		try {
			if (addAlternative(req.alternativeID, req.choiceID, req.altDesc)) {
				response = new AddAlternativeResponse("" + req.alternativeID);
			} else {
				response = new AddAlternativeResponse("" + req.alternativeID, 422);
			}
		} catch (Exception e) {
			response = new AddAlternativeResponse("Unable to add alternative: " +  req.alternativeID + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
