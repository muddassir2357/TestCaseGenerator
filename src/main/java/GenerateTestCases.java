import java.util.ArrayList;
import java.util.List;

public class GenerateTestCases {
	public static int count = 0;

	public static void main(String[] args) {
		List<Action> psActions = new ArrayList<Action>();// read Photoshop Actions
		psActions.add(new Action().setName("Create Layer"));
		psActions.add(new Action().setName("Apply Mask"));
		psActions.add(new Action().setName("Create Adjustment Layer"));
		psActions.add(new Action().setName("Use Paint brush"));
		psActions.add(new Action().setName("Apply Gradient"));
		List<Action> parasActions = new ArrayList<Action>();// Read paras Actions
		parasActions.add(new Action().setName("Create Pattern"));
		parasActions.add(new Action().setName("Select Mirror Copies"));
		parasActions.add(new Action().setName("Create Separation"));
		parasActions.add(new Action().setName("Update Separation"));
		parasActions.add(new Action().setName("Select Tile Type"));

		new GenerateTestCases().printTestCases(psActions, parasActions);

	}

	private void printTestCases(List<Action> psActions, List<Action> parasActions) {
		generateAllParasWorkflows(parasActions, 0, new ArrayList<Action>(), 0, parasActions.size());// Generate all
		//generateAllParasPhotoshopWorkflows(parasActions,psActions,0,new ArrayList<Action>(),(parasActions.size()+psActions.size()),false);																							// paras workflows
																									// of length n
		// Generate all paras and photoshop workflow of length(n+m)

	}

	private void generateAllParasWorkflows(List<Action> parasActions, int start_index, List<Action> combination_arr,
			int index, int fill) {

		if (combination_arr.size() >= fill) {
			count++;
			System.out.println("*****************TestCase " + count + "*****************");
			for (int i = 0; i < combination_arr.size(); i++) {
				System.out.println(i + ". " + combination_arr.get(i).name);
			}
		}
		for (int i = start_index; i < parasActions.size(); i++) {
			ArrayList<Action> possible_com = new ArrayList<Action>(combination_arr);
			possible_com.add(parasActions.get(i));
			List<Action> remainingActions = new ArrayList<Action>(parasActions);
			remainingActions.remove(i);
			generateAllParasWorkflows(remainingActions, start_index, possible_com, index++, fill);
		}

	}

	private void generateAllParasPhotoshopWorkflows(List<Action> parasActions,List<Action> photoshopActions, int start_index,
			List<Action> combination_arr, int fill,boolean state) {

		if (combination_arr.size() >= fill) {
			count++;
			System.out.println("*****************TestCase " + count + "*****************");
			for (int i = 0; i < combination_arr.size(); i++) {
				System.out.println(i + ". " + combination_arr.get(i).name);
			}
			return;
		}
		int j=0;
		int k=0;
		for (int i = start_index; i < (parasActions.size()+photoshopActions.size()) ; i++) {
			ArrayList<Action> possible_com = new ArrayList<Action>(combination_arr);
			List<Action> remainingParasActions = new ArrayList<Action>(parasActions);
			List<Action> remainingPSActions = new ArrayList<Action>(photoshopActions);
			if(state ) {
				if(j>=remainingParasActions.size())
					return;
				possible_com.add(remainingParasActions.get(j));
				remainingParasActions.remove(j);
				j++;
				}
			else if(!state ) {
				if(k>=remainingPSActions.size())
					return;
				possible_com.add(remainingPSActions.get(k));
				remainingPSActions.remove(k);
				k++;
			}			
			generateAllParasPhotoshopWorkflows(remainingParasActions,remainingPSActions, start_index, possible_com, fill,!state);
		}

	}

}
