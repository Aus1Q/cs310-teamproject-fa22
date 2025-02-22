package edu.jsu.mcis.cs310.tas_fa22;

public class Department {

    private final String description;
    private final Integer terminalid,id;

    public Department(Integer id, String description, Integer terminalid) {
        this.id = id;
        this.description = description;
        this.terminalid = terminalid;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    public Integer getTerminalid(){
        return terminalid;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');
        s.append(",").append(" Terminal ID: ").append(terminalid);
        
        

        return s.toString();

    }

}
