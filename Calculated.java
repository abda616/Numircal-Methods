package NUM;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Calculated {
    private final SimpleDoubleProperty iteration;
    private  SimpleDoubleProperty x_lower;
    private  SimpleDoubleProperty x_upper;
    private final SimpleDoubleProperty root;
    private  SimpleDoubleProperty f_lower;
    private  SimpleDoubleProperty f_upper;
    private final SimpleDoubleProperty f_root;
    private  SimpleDoubleProperty fLower_fUpper;
    private final SimpleDoubleProperty error;

    public Calculated(Integer iteration, Double x_lower, Double x_upper, Double root, Double f_lower, Double f_upper, Double f_root, Double fLower_fUpper, Double error) {
        this.iteration = new SimpleDoubleProperty(iteration) ;
        this.x_lower = new SimpleDoubleProperty(x_lower);
        this.x_upper = new SimpleDoubleProperty(x_upper);
        this.root = new SimpleDoubleProperty(root);
        this.f_lower = new SimpleDoubleProperty(f_lower);
        this.f_upper = new SimpleDoubleProperty(f_upper);
        this.f_root = new SimpleDoubleProperty(f_root);
        this.fLower_fUpper = new SimpleDoubleProperty(fLower_fUpper);
        this.error = new SimpleDoubleProperty(error);
    }
    public Calculated(Integer iteration, Double x_lower, Double x_upper, Double root, Double f_lower, Double f_upper, Double f_root,Double error) {
        this.iteration = new SimpleDoubleProperty(iteration) ;
        this.x_lower = new SimpleDoubleProperty(x_lower);
        this.x_upper = new SimpleDoubleProperty(x_upper);
        this.root = new SimpleDoubleProperty(root);
        this.f_lower = new SimpleDoubleProperty(f_lower);
        this.f_upper = new SimpleDoubleProperty(f_upper);
        this.f_root = new SimpleDoubleProperty(f_root);
        this.error = new SimpleDoubleProperty(error);
    }
    public Calculated(Integer iteration, Double root, Double f_lower, Double f_upper, Double f_root, Double error) {
        this.iteration = new SimpleDoubleProperty(iteration) ;
        this.root = new SimpleDoubleProperty(root);
        this.f_lower = new SimpleDoubleProperty(f_lower);
        this.f_upper = new SimpleDoubleProperty(f_upper);
        this.f_root = new SimpleDoubleProperty(f_root);
        this.error = new SimpleDoubleProperty(error);
    }
    public Calculated(Integer iteration, Double root, Double f_root, Double error) {
        this.iteration = new SimpleDoubleProperty(iteration);
        this.root = new SimpleDoubleProperty(root);
        this.f_root = new SimpleDoubleProperty(f_root);
        this.error = new SimpleDoubleProperty(error);
    }

    public double getIteration() {
        return iteration.get();
    }
    public DoubleProperty iterationProperty() {
        return iteration;
    }
    public void setIteration(double iteration) {
        this.iteration.set(iteration);
    }
    public double getRoot() {
        return root.get();
    }
    public DoubleProperty rootProperty() {
        return root;
    }
    public void setRoot(double root) {
        this.root.set(root);
    }
    public double getX_lower() {
        return x_lower.get();
    }
    public DoubleProperty x_lowerProperty() {
        return x_lower;
    }
    public void setX_lower(double x_lower) {
        this.x_lower.set(x_lower);
    }
    public double getX_upper() {
        return x_upper.get();
    }
    public DoubleProperty x_upperProperty() {
        return x_upper;
    }
    public void setX_upper(double x_upper) {
        this.x_upper.set(x_upper);
    }
    public double getF_lower() {
        return f_lower.get();
    }
    public DoubleProperty f_lowerProperty() {
        return f_lower;
    }
    public void setF_lower(double f_lower) {
        this.f_lower.set(f_lower);
    }
    public double getF_upper() {
        return f_upper.get();
    }
    public DoubleProperty f_upperProperty() {
        return f_upper;
    }
    public void setF_upper(double f_upper) {
        this.f_upper.set(f_upper);
    }
    public double getF_root() {
        return f_root.get();
    }
    public DoubleProperty f_rootProperty() {
        return f_root;
    }
    public void setF_root(double f_root) {
        this.f_root.set(f_root);
    }
    public double getfLower_fUpper() {
        return fLower_fUpper.get();
    }
    public DoubleProperty fLower_fUpperProperty() {
        return fLower_fUpper;
    }
    public void setfLower_fUpper(double fLower_fUpper) {
        this.fLower_fUpper.set(fLower_fUpper);
    }
    public double getError() {
        return error.get();
    }
    public DoubleProperty errorProperty() {
        return error;
    }
    public void setError(double error) {
        this.error.set(error);
    }
}