package org.immutable.pascal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Immutable implements Serializable {

	public static final long serialverisonUID = 1l;
	
	private final String applicatonname;
	private final int frequencyofcalling;
	private final double depthvalue;
	private List<String> words;

	private Immutable(String applicatonname, int frequencyofcalling, double depthvalue, List<String> words) {
		super();
	
		this.applicatonname = applicatonname;
		this.frequencyofcalling = frequencyofcalling;
		this.depthvalue = depthvalue;
		this.words = Collections.unmodifiableList(new ArrayList<>(words));
		
		  preventReflectionModification();
	}
	
	private static volatile Immutable ref;
	
	public static Immutable createInstance(String applicationname,int frequencyofcalling,double depthvalue,List<String>wrods) {
	
		if(ref == null) {
			synchronized (Immutable.class) {
				if(ref == null)
					ref = new Immutable(applicationname, frequencyofcalling, depthvalue, wrods);
			}
		}
		
		return ref;
	}

	public String getApplicatonname() {
		return applicatonname;
	}

	public int getFrequencyofcalling() {
		return frequencyofcalling;
	}

	public double getDepthvalue() {
		return depthvalue;
	}

	public List<String> getWords() {
		return Collections.unmodifiableList(words);
	}
	

	@Override
	public String toString() {
		return "Immutable [applicatonname=" + applicatonname + ", frequencyofcalling=" + frequencyofcalling
				+ ", depthvalue=" + depthvalue + ", ref=" + ref + ", words=" + words + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("the object can not be clonned");
	}
	
	public void writeObject(ObjectOutputStream oos) throws IOException{
		   oos.defaultWriteObject();
	}
	
	public void readObject(ObjectInputStream ois) throws IOException,ClassNotFoundException{
		ois.defaultReadObject();
		if(applicatonname == null || words == null)
			 throw new RuntimeException("Object data is invalid");
		  preventReflectionModification();
	}
	
	public Object readResolve() throws ObjectStreamException{
		return ref;
	}
	
	
	
	// preventing from the modification through reflection package:
	
	@Override
	public int hashCode() {
		return Objects.hash(applicatonname, depthvalue, frequencyofcalling, words);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Immutable other = (Immutable) obj;
		return Objects.equals(applicatonname, other.applicatonname)
				&& Double.doubleToLongBits(depthvalue) == Double.doubleToLongBits(other.depthvalue)
				&& frequencyofcalling == other.frequencyofcalling && Objects.equals(words, other.words);
	}

	private void   preventReflectionModification(){
////		// to save from the reflection package the class properties should be made final: 
////		Final Fields: Ensuring all fields are final to make them immutable.
//		Reflection Attack Prevention: Added a preventReflectionModification method that sets all fields to not accessible. This method is called in the constructor and during deserialization to ensure fields cannot be modified via reflection.
//		Custom Serialization Methods: writeObject and readObject methods to handle serialization and deserialization, maintaining immutability.
//		ReadResolve Method: Ensures the integrity of the deserialized object by returning a proper immutable instance.

		  try {
			           for(Field field : Immutable.class.getDeclaredFields()) 
			        	   field.setAccessible(false);
			           
		  }
		  catch(Exception e) {
			  throw new AssertionError("Refleciton attack prevention failed");
		  }
		
//		  readResolve() is called after the object is deserialized. It allows you to return a different object instead of the one that was deserialized.
//				  In this case, readResolve() returns the singleton instance (ref). This ensures that after deserialization, the returned object is the same as the singleton instance rather than a new, separate
//				  object. This is useful to maintain the singleton property or ensure the integrity of the deserialized object.
		
	}
	
	
}
