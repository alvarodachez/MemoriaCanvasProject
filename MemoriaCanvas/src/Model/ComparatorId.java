package Model;

import java.util.Comparator;

public class ComparatorId implements Comparator<Tarea>{

	@Override
	public int compare(Tarea arg0, Tarea arg1) {
		// TODO Auto-generated method stub
		return ((Integer)arg0.getIdTarea()).compareTo((Integer)arg1.getIdTarea());
	}

}
