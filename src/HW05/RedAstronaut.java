package HW05;

public class RedAstronaut extends Player implements Impostor {
    String skill;

    public RedAstronaut(String name, int susLevel, String skill){
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name){
        this(name, 15, "experienced");
    }

    public String toString(){
        String output;
        if (this.isFrozen()){
            output = String.format("My name is %s, and I have a suslevel of %d. I am currently frozen. I am an %s player!",
                this.getName(), this.getSusLevel(), this.skill);
        } else {
            output = String.format("My name is %s, and I have a suslevel of %d. I am currently not frozen. I am an %s player!",
                    this.getName(), this.getSusLevel(), this.skill);
        }
        if (this.getSusLevel() > 15){
            return output.toUpperCase();
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut){
            RedAstronaut r = (RedAstronaut) o;
            if (r.getName().equals(this.getName()) && r.getSusLevel() == this.getSusLevel() && r.skill.equals(this.skill) && (r.isFrozen() == this.isFrozen())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void freeze(Player p) {
        if (p.isFrozen() || this.isFrozen() || (p instanceof Impostor)) return;
        else {
            if (p.getSusLevel() > this.getSusLevel()){
                p.setFrozen(true);
            } else {
                this.setSusLevel(this.getSusLevel() * 2);
            }
            gameOver();
        }
    }

    @Override
    public void sabotage(Player p) {
        if (!this.isFrozen() && !(p instanceof Impostor) && !p.isFrozen()){ // We are not frozen, and we are not sabotaging another impostor
            if (this.getSusLevel() < 20){
                p.setSusLevel((int) (p.getSusLevel() * 1.5d));
            } else {
                p.setSusLevel((int) (p.getSusLevel() * 1.25d));
            }
        }
    }

    @Override
    public void emergencyMeeting() {
        if (!this.isFrozen()){
            int maxSus = 0;
            Player[] playerArray = getPlayers();
            boolean doubleSusRating = false;
            for (int i=0; i < getPlayers().length; i++){
                if (!(playerArray[i].isFrozen()) && maxSus < playerArray[i].getSusLevel() && (playerArray[i] != this)){
                    maxSus = playerArray[i].getSusLevel();
                    doubleSusRating = false;
                } else if (maxSus == playerArray[i].getSusLevel()) {
                    doubleSusRating = true;
                }
            }
            // Now we know if there is a tie for suspicion
            if (!doubleSusRating){
                for (int i=0; i < getPlayers().length; i++){
                    if (playerArray[i].getSusLevel() == maxSus){
                        playerArray[i].setFrozen(true);
                        break;
                    }
                }
            }
            gameOver();
        }
    }
}
