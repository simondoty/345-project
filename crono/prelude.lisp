%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% boolean functions
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
(define not (bool)
  (if (= bool nil)
    t
    nil))

% TODO: Rewrite these to take an arbitrary number of arguments.
(define and (a b)
  (if a
    b
    nil))

(define or (a b)
  (if a
    t
    b))
% /boolean

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% higher order functions
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
(define map (f l)
  (if (= l nil)
    nil
    (cons (f (car l)) (map f (cdr l)))))

(define _foldl (f l s)
  (if (= l nil)
    s
    (_foldl f (cdr l) (f s (car l)))))

(define foldl (f l)
  (if (= l nil)
    nil
    (_foldl f (cdr l) (car l))))

(define _foldr (f l s)
  (if (= l nil)
    s
    (f (car l) (_foldr f (cdr l) s))))

(define foldr (f l)
  (if (= l nil)
    nil
    (_foldr f (cdr l) (car l))))
%/higher order

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% math functions
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
(define ! (n)
  (if (= n 0)
    1
    (* (! (- n 1)) n)))

(define mod (x y)
  (if (< x y)
    x
    (mod (- x y) y)))
% /math
