
public class FunctionFactory {
	class plus implements ICompute {
		@Override
		public double compute(double leftNum, double rightNum) {
			return leftNum + rightNum;
		}
	}
	
	class subtract implements ICompute{
		@Override
		public double compute(double leftNum, double rightNum) {
			return leftNum - rightNum;
		}	
	}
	
	class multiply implements ICompute{
		@Override
		public double compute(double leftNum, double rightNum) {
			return leftNum * rightNum;
		}	
	}
	
	class divide implements ICompute{
		@Override
		public double compute(double leftNum, double rightNum) {
			return leftNum / rightNum;
		}
	}

}
