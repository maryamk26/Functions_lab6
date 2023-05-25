import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {
        System.out.println("(1) Y = cosX = \n");
        func functional = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                System.out.println(Math.cos(i));
            }
        };
        CalcFUNC(functional);

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\n(2) 2*sqrt(abs(X - 1)) + 1 = \n");
        functional = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                double y = 2*Math.sqrt(Math.abs(i - 1)) + 1;
                System.out.println(y);
            }
        };
        CalcFUNC(functional);

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\n(3) -(X/pi)^2 -2*x + 5*pi = \n");
        functional = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                double y = -1*Math.pow(i/Math.PI,2) -2*i + 5*Math.PI;
                System.out.println(y);
            }
        };
        CalcFUNC(functional);

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\n(4) (X/pi*k - 1)^2 , For k=1 to k=100 : \n");
        functional = () -> {
            double y =0;
            double i = -2*Math.PI;
            for (int k = 1; i <= 100; i++) {
                if (i > 2*Math.PI) {
                    break;
                }
                y += Math.pow((i/(Math.PI * k)) - 1,2);
                System.out.println(y);
                System.out.println("i = "+i);
                i += Math.PI/6;
            }
        };
        CalcFUNC(functional);

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\n(5) Y = 1/4sin^2*X + 1 if x < 0, else then y = 1/2cosX - 1 : \n");
        functional = () -> {
            double y;
            for (double i = -2*Math.PI; i <2*Math.PI ; i+= Math.PI/6) {
                if (i < 0) {
                    y = (1.0/4.0)*Math.pow(Math.sin(i),2) + 1;
                    System.out.println(y);
                } else {
                    y = (1.0/2.0)*Math.cos(i) -1;
                    System.out.println(y);
                }
            }
        };
        CalcFUNC(functional);


        System.out.println("-----------------------------------------------------------------------");


        System.out.println("\n----------- TASK 2 -----------\n");
        ArrayList<ArrayList<Double>> equations = new ArrayList<>();
        //Randomly generated points arrayList
        ArrayList<Double> points = generateRandomPoints(10);

        DoubleUnaryOperator[] funcs = new DoubleUnaryOperator[]{
                i -> Math.cos(i),
                i -> 2 * Math.sqrt(Math.abs(i - 1)) + 1,
                i -> -1*Math.pow(i/Math.PI,2) -2*i + 5*Math.PI,
                i -> {
                    double y = 0;
                    i = -2 * Math.PI;
                    for (int k = 1; i <= 100; i++) {
                        if (i > 2 * Math.PI) {
                            break;
                        }
                        y += Math.pow((i / (Math.PI * k)) - 1, 2);
                        i += Math.PI / 6;

                    }
                    return y;
                },
                i -> {
                    double y =0;
                    for (i = -2*Math.PI; i <2*Math.PI ; i+= Math.PI/6) {
                        if (i < 0) {
                            y = (1.0/4.0)*Math.pow(Math.sin(i),2) + 1;
                        } else {
                            y = (1.0/2.0)*Math.cos(i) -1;

                        }
                    }
                    return y;
                }
        };

        System.out.println(" RANDOM ARRAY : ");
        for (Double P : points) {
            System.out.print(P+" ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");

        int i =1;
        for (DoubleUnaryOperator function : funcs) {
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;


            for (double point : points) {
                double result = function.applyAsDouble(point);
                min = Math.min(min,result);
                max = Math.max(max,result);
            }

            System.out.println("\nFunction: "+ i);
            System.out.println("MIN. VALUE : " + min);
            System.out.println("MAX. VALUE : " + max+"\n");
            i++;

            System.out.println("-----------------------------------------------------------------------");

        }
        doFunctions(equations,funcs);

    }

    public static void CalcFUNC(func FUNC) {
        FUNC.calculate();
    }

    public static void doFunctions(ArrayList<ArrayList<Double>> equations, DoubleUnaryOperator[] functions) {
        int negativenos = 0;
        int rangenos = 0;
        DoublePredicate negative = value -> value < 0;
        DoublePredicate range = value -> (value > -1 && value < 1);


        for (double l = -2*Math.PI; l <= 2*Math.PI; l += Math.PI/6) {
            ArrayList<Double> results = new ArrayList<>();
            for (DoubleUnaryOperator function : functions) {
                double result = function.applyAsDouble(l);
                System.out.println("\n"+result);
                if (negative.test(result)) {
                    System.out.println("IN NEGATIVE");
                    negativenos++;

                }

                System.out.println("-----------------------------------------------------------------------");

                if (range.test(result)) {
                    System.out.println("IN RANGE"+"\n");
                    rangenos++;

                }
                results.add(result);
            }
            System.out.println("-----------------------------------------------------------------------");
            equations.add(results);
        }

        for (ArrayList<Double> equation : equations) {
            System.out.println("\n"+equation);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\nNEGATIVE NUMBERS : "+negativenos);
        System.out.println("IN RANGE [-1,1] NUMBERS : "+rangenos);
        System.out.println("-----------------------------------------------------------------------");

        ArrayList<ArrayList<Double>> arrangedList = arrangeArray(equations);
        System.out.println("\n------ARRANGED ARRAY------ :");
        for (ArrayList<Double> array : arrangedList) {
            System.out.println("\n"+array);
        }
    }

    public static ArrayList<ArrayList<Double>> arrangeArray(ArrayList<ArrayList<Double>> arrayList) {

        int maxArraySize = getMaxSize(arrayList);
        ArrayList<ArrayList<Double>> arrangedArrayList = new ArrayList<>();

        for (int i = 0; i < maxArraySize; i++) {
            ArrayList<Double> subArrayList = new ArrayList<>();
            for (ArrayList<Double> list : arrayList) {
                if (i < list.size()) {
                    subArrayList.add(list.get(i));
                }
            }
            arrangedArrayList.add(subArrayList);
        }
        return arrangedArrayList;
    }

    public static int getMaxSize(ArrayList<ArrayList<Double>> arrayList) {
        int maximumsize = 0;

        for (ArrayList<Double> sublist : arrayList) {
            if (sublist.size() > maximumsize) {
                maximumsize = sublist.size();
            }
        }
        return maximumsize;
    }

    public static ArrayList<Double> generateRandomPoints(int n) {
        Random r = new Random();
        ArrayList<Double> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double point = r.nextDouble(-10.0,10.0);
            points.add(point);
        }
        return points;
    }
}
