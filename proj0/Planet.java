public class Planet{
double xxPos;
double yyPos;
double xxVel;
double yyVel;
double mass;
String imgFileName;
    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName= imgFileName;

    }


    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass =p.mass;
        this.imgFileName= p.imgFileName;
    }
    // xp = current x position , yp = current y position, xV = current velocity in X direction, yV = current velocity in Y direction, m is mass
    // img = planet file. 

}
