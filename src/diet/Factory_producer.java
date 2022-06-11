package diet;

public class Factory_producer {

    public Animal_factory getFactory(String factory){
        if(factory.equals("Carnivore"))
            return new Carnivore_factory();
        if(factory.equals("Omnivore"))
            return new Omnivore_factory();
        if(factory.equals("Herbivore"))
            return new Herbivore_factory();
        else
            return null;
    }

}
