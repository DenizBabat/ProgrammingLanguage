/*!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%!PART_1*/

flight(istanbul, trabzon).
flight(istanbul,kars).
flight(istanbul,ankara).
flight(istanbul,gaziantep).
flight(istanbul,konya).
flight(istanbul,antalya).
flight(istanbul,izmir).

flight(trabzon,istanbul).
flight(trabzon,ankara).

flight(kars,istanbul).
flight(kars, ankara).

flight(ankara, istanbul).
flight(ankara, trabzon).
flight(ankara, kars).
flight(ankara, izmir).
flight(ankara, konya).

flight(konya, ankara).
flight(konya, istanbul).

flight(gaziantep, istanbul).

flight(antalya, istanbul).

flight(izmir, istanbul).
flight(izmir, ankara).

flight(edirne, edremit).

flight(edremit, edirne).
flight(edremit, erzincan).

flight(erzincan, edremit).

route(A, B) :-
	flight(A,B).
	
/*%!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%!PART_2*/

distance(istanbul, trabzon,902).
distance(istanbul,kars,1189).
distance(istanbul,ankara,350 ).
distance(istanbul,gaziantep,848).
distance(istanbul,konya,461).
distance(istanbul, antalya, 481).
distance(istanbul,izmir,328).

distance(trabzon,istanbul,902).
distance(trabzon,ankara,593 ).

distance(kars,istanbul,1186).
distance(kars, ankara,872).

distance(ankara, istanbul,350).
distance(ankara, trabzon, 593).
distance(ankara, kars, 872).
distance(ankara, izmir, 521 ).
distance(ankara, konya, 231).

distance(konya, ankara, 231).
distance(konya, istanbul, 461).

distance(gaziantep, istanbul, 848).

distance(antalya, istanbul, 481).

distance(izmir, istanbul, 328).
distance(izmir, ankara, 521).

distance(edirne, edremit, 225 ).

distance(edremit, edirne, 225).
distance(edremit, erzincan, 1044 ).

distance(erzincan, edremit,1044 ).


sroute(A,B,C):-
	distance(A,B,C).
	
/*%!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%!PART_3*/

when(102, 10).
when(108, 12).
when(341, 14).
when(455, 17).
when(452, 17).

where(102, z23).
where(108, z11).
where(341, z06).
where(455, 207).
where(452, 207).

enroll(a,102).
enroll(a, 108).
enroll(b, 102).
enroll(c, 108).
enroll(d, 341).
enroll(e, 455).

/*PART_3.1*/
schedule(S,P,T):-
	enroll(S,X),
	where(X, P),
	when(X,T).
	
/*PART_3.2*/
usage(P,T):-
	where(X,P),
	when(X,T).
	
/*PART_3.3*/
conflict(X,Y):-
	where(X,A),
	where(Y,B),
	when(X,C),
	when(Y,D),
	A == B,
	C==D.
	
/*PART_3.4*/
meet(X,Y):-
	enroll(X,A),
	enroll(Y,B),
	when(X,C),
	when(Y,D),
	A == B,
	C==D.
/*%!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%!PART_4.1*/

add([], 0).  
add([First | Rest], S) :-
	Firstadd is First,	
    add(Rest, X),
	S is Firstadd+X.
	
/*%!PART_4.2*/	

=(X, Y, R) :- X == Y,    !, R = true.
=(X, Y, R) :- ?=(X, Y),  !, R = false. % syntactical olarak farklı
=(X, Y, R) :- X \= Y,    !, R = false. % semantical olarak farklı
=(X, Y, R) :- R == true, !, X = Y.
=(X, X, true).

=(X, Y, false) :-
   dif(X, Y).

if_(C_1, Then_0, Else_0) :-
   call(C_1, Truth),
   functor(Truth,_,0),  % Control
   ( Truth == true -> Then_0 ; Truth == false, Else_0 ).

isMember([],_,false).

isMember([X|Xs],E,Truth) :-
   if_(E = X, Truth = true, isMember(Xs,E,Truth)).

unique([],[]).
unique([X|Xs],Ys) :-
    if_(isMember(Xs,X), Ys = Ys1, Ys = [X|Ys1]),
    unique(Xs,Ys1).
	
/*%!PART_4.3*/	
flatten([], []).
flatten([L|Ls], FlatL) :-
    flatten(L, NewL),
    flatten(Ls, NewLs),
    append(NewL, NewLs, FlatL).
flatten(L, [L]).
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	