avg_temp(phx,100).
avg_temp(sf, 68).


avg_temp_cells(Location, C_Temp) :-
	avg_temp(Location, F_Temp),
	C_Temp is (F_Temp-32) * 5/9.