package fr.univmrs.tagc.logicalfunction.operators;

import java.util.Stack;

import fr.univmrs.tagc.javaMDD.MDDFactory;
import fr.univmrs.tagc.logicalfunction.BooleanNode;
import fr.univmrs.tagc.logicalfunction.OperatorFactory;

/**
 * Factory for the "not" operator.
 * 
 * @author Aurelien Naldi
 */
public class NotOperatorFactory implements OperatorFactory {

	/**
	 * Use this single instance object if you need this factory
	 */
	public static final NotOperatorFactory FACTORY = new NotOperatorFactory();
	
	public static final int PRIORITY = 1;
	public static final String SYMBOL = "!";

	private NotOperatorFactory() {
		// single-instance: no constructor
	}
	
	@Override
	public String getSymbol() {
		return SYMBOL;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public BooleanNode getNode(Stack<BooleanNode> stack) {
		return new NotOperator(stack);
	}
	
	public BooleanNode getNode(BooleanNode n) {
		return new NotOperator(n);
	}
}

/**
 * The "not" operator itself.
 * 
 * @author Fabrice Lopez: initial implementation
 * @author Aurelien Naldi: adaptation
 */
class NotOperator extends AbstractUnaryOperator {

	public NotOperator(Stack<BooleanNode> stack) {
		super(stack);
	}

	public NotOperator(BooleanNode f) {
		super(f);
	}

	@Override
	public String getSymbol() {
		return NotOperatorFactory.SYMBOL;
	}
  
	@Override
	public int getMDD(MDDFactory factory, boolean reversed) {
		// FIXME: the "reversed" trick works for simple cases but is ugly and will likely break for complex cases
		return arg.getMDD(factory, !reversed);
	}
}
