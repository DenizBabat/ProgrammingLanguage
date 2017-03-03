square_3([], []).  
square_3([First | Rest], [Square | SquareRest]) :-
        FirstSquared is First + First,
        square_3(Rest, SquareRest),
        Square = FirstSquared.