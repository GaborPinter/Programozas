package hu.ak_akademia.basicgame.entity;

import hu.ak_akademia.basicgame.Coordinates;
import hu.ak_akademia.basicgame.Level;

public interface Entity {
	
	 String getMark();

	 void setMark(String mark) ;

	 Coordinates getCoordinates() ;

	 void setCoordinates(Coordinates coordinates) ;

	 Level getLevel() ;

	 boolean update() ;

}
