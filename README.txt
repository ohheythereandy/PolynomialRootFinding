This project uses different methods to find roots of polynomial functions. By default, it uses the bisection method
but it can be modified to use other algorithms such as Newton's, Secant, and Hybrid by passing different arguments
in the command line (-newt, -sec, -hybrid).

The polynomials must be provided in a separate file with extension ".pol", and they must be placed in the "/assets"
directory. The solution will be placed in the same directory folder, with an extension of ".sol". The format of the
solution file is:
root iterations outcome
Where root is the last approximation, iterations is the total iterations completed, and outcome is "Success" if it
converged, or "Fail" if it didn't. The format of the input file is:
n
a(n) a(n-1) a(n-2) ... b
where a(n) is the coefficient of the nth degree.

The total default iterations performed is 10,000, but it can also be modified by using the -maxIter flag and providing a
number following it.

Before using, open the project "src" directory and compile all java files there using :
"javac Polynomial.java RootFinder.java polRoot.java"

Some examples of usage is as follows:

Performing bisection using default max iterations and providing a function in "fun1.pol"
    java polRoot 0 1 fun1.pol

Performing newtons using default max iterations
    java polRoot -newt 0 fun4.pol