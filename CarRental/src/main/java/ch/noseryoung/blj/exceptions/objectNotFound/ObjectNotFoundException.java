package ch.noseryoung.blj.exceptions.objectNotFound;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
  public ObjectNotFoundException() {
    super("The object by this id was not found.");
  }

}
