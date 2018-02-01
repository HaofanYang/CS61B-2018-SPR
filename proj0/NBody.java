public class NBody{
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		for(int time = 0; time <= T; time += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int count = 0; count < planets.length; count++){
				xForces[count] = planets[count].calcNetForceExertedByX(planets);
				yForces[count] = planets[count].calcNetForceExertedByY(planets);
			}
			for(int count = 0; count < planets.length; count++){
				planets[count].update(dt, xForces[count],yForces[count]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}


	}

	public static double readRadius(String fileName){
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();

	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int numOfPlanets = in.readInt();
		in.readDouble();
		Planet[] ps = new Planet[numOfPlanets];
		for (int count = 0; count < numOfPlanets; count++){
			ps[count] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
				in.readDouble(), in.readDouble(), in.readString());
		}
		return ps;
		
	}
}