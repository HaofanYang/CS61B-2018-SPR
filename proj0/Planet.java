public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = "images/"+img;

	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p){
		double xDisSqur = Math.pow((this.xxPos - p.xxPos),2);
		double yDisSqur = Math.pow((this.yyPos - p.yyPos),2);
		double distance = Math.pow((xDisSqur + yDisSqur), 0.5);
		return distance;

	}

	public double calcForceExertedBy(Planet p){
		double gCons = 6.67 * (Math.pow(10,-11));
		double force = (gCons * p.mass * this.mass) / Math.pow(this.calcDistance(p),2);
		return force;
		
	}

	public double calcForceExertedByX(Planet p){
		double cosSigma = -(this.xxPos - p.xxPos) / this.calcDistance(p);
		double forceByX = this.calcForceExertedBy(p) * cosSigma;
		return forceByX;
		
	}

	public double calcForceExertedByY(Planet p){
		double sinSigma = -(this.yyPos - p.yyPos) / this.calcDistance(p);
		double forceByY = this.calcForceExertedBy(p) * sinSigma;
		return forceByY;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double netXForces = 0.0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}
			netXForces += this.calcForceExertedByX(p);
		}
		return netXForces;
		
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double netYForces = 0.0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}
			netYForces += this.calcForceExertedByY(p);
		}
		return netYForces;
		
	}

	public void update(double t, double fX, double fY){
		double accXSpeed = fX / this.mass;
		double accYSpeed = fY / this.mass;
		this.xxVel += (t * accXSpeed);
		this.yyVel += (t * accYSpeed);
		this.xxPos += (t * this.xxVel);
		this.yyPos += (t * this.yyVel);
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
	}

}