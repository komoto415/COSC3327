package tournament;

// Fifty-two potential participants to test and make a manageable ASCII art toString()
public enum Letters {
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, t, s, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L,
    M, N, O, P, Q, R, T, S, U, V, W, X, Y, Z;

    public static Letters translate(String ch) {
        switch (ch) {
            case "a":
                return Letters.a;
            case "b":
                return Letters.b;
            case "c":
                return Letters.c;
            case "d":
                return Letters.d;
            case "e":
                return Letters.e;
            case "f":
                return Letters.f;
            case "g":
                return Letters.g;
            case "h":
                return Letters.h;
            case "i":
                return Letters.i;
            case "j":
                return Letters.j;
            case "k":
                return Letters.k;
            case "l":
                return Letters.l;
            case "m":
                return Letters.m;
            case "n":
                return Letters.n;
            case "o":
                return Letters.o;
            case "p":
                return Letters.p;
            case "q":
                return Letters.q;
            case "r":
                return Letters.r;
            case "s":
                return Letters.s;
            case "t":
                return Letters.t;
            case "u":
                return Letters.u;
            case "v":
                return Letters.v;
            case "w":
                return Letters.w;
            case "x":
                return Letters.x;
            case "y":
                return Letters.y;
            case "z":
                return Letters.z;
            case "A":
                return Letters.A;
            case "B":
                return Letters.B;
            case "C":
                return Letters.C;
            case "D":
                return Letters.D;
            case "E":
                return Letters.E;
            case "F":
                return Letters.F;
            case "G":
                return Letters.G;
            case "H":
                return Letters.H;
            case "I":
                return Letters.I;
            case "J":
                return Letters.J;
            case "K":
                return Letters.K;
            case "L":
                return Letters.L;
            case "M":
                return Letters.M;
            case "N":
                return Letters.N;
            case "O":
                return Letters.O;
            case "P":
                return Letters.P;
            case "Q":
                return Letters.Q;
            case "R":
                return Letters.R;
            case "S":
                return Letters.S;
            case "T":
                return Letters.T;
            case "U":
                return Letters.U;
            case "V":
                return Letters.V;
            case "W":
                return Letters.W;
            case "X":
                return Letters.X;
            case "Y":
                return Letters.Y;
            case "Z":
                return Letters.Z;
            default:
                return null;
        }
    }
}
