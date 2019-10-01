/**
 * 
 */
package widgets;

/**
 * @author nicolasmakaroff
 *
 */
public enum OperationMode {
	CREATION,
	TRANSFORMATION;
	
	
	
	public final static int NbMode = 2;
	
public static OperationMode fromInt(int i) {
	switch(i) {
	case 0 : 
		return CREATION;
	case 1 :
		return TRANSFORMATION;
	default : return CREATION;
	}
}

public int toInt() {
	switch (this) {
	case CREATION :
		return 0;
		
	case TRANSFORMATION : 
		return 1;
		
	}
	throw new AssertionError("OperationMode unknown" + this);
}

public String toStr() {
	switch(this) {
	case CREATION:
		return new String("Creation");
		
	case TRANSFORMATION : 
		return new String("Transformation");
	}
	throw new AssertionError("OperationMode unknown" + this); 
}

public OperationMode nextMode() {
	switch(this) {
	case CREATION : 
		return TRANSFORMATION;
	
	case TRANSFORMATION : 
		return CREATION;
	}
	throw new AssertionError("OperationMode unknown" + this);
}

}
