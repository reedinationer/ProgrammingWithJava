package HW05;

public class BlueAstronaut extends Player implements Crewmate{
    private int numTasks;
    private int taskSpeed;
    private boolean allTasksCompleted = false;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed){
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }
    public BlueAstronaut(String name){
        this(name, 15, 6, 10);
    }

    public String toString(){
        String output;
        if (this.isFrozen()){
            output = String.format("My name is %s, and I have a suslevel of %d. I am currently frozen. I have %d left over.",
                this.getName(), this.getSusLevel(), this.numTasks);
        } else {
            output = String.format("My name is %s, and I have a suslevel of %d. I am currently not frozen. I have %d left over.",
                    this.getName(), this.getSusLevel(), this.numTasks);
        }
        if (this.getSusLevel() > 15){
            return output.toUpperCase();
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut){
            BlueAstronaut b = (BlueAstronaut) o;
            if (b.getName().equals(this.getName()) && b.getSusLevel() == this.getSusLevel() && b.numTasks == this.numTasks && (b.isFrozen() == this.isFrozen()) && b.taskSpeed == this.taskSpeed) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void completeTask() {
        if (!this.isFrozen()){
            if (this.taskSpeed > 20){
                this.numTasks -= 2;
            } else {
                this.numTasks -= 1;
            }
            if (this.numTasks < 0){
                this.numTasks = 0;
            }
            if (this.numTasks == 0 && !allTasksCompleted){
                allTasksCompleted = true;
                System.out.println("I have completed all my tasks");
            }
            this.setSusLevel((int) (this.getSusLevel() * 0.5d));
        }
    }

    @Override
    public void emergencyMeeting() {
        if (!this.isFrozen()){
            int maxSus = 0;
            Player[] playerArray = getPlayers();
            boolean doubleSusRating = false;
            for (int i=0; i < getPlayers().length; i++){
                if (!(playerArray[i].isFrozen()) && maxSus < playerArray[i].getSusLevel()){
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
