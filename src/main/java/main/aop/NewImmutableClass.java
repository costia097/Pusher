package main.aop;

public class NewImmutableClass {
    public NewImmutableClass(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public NewImmutableClass setName(String name) {
        return new NewImmutableClass(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewImmutableClass that = (NewImmutableClass) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
