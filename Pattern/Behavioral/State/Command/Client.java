package Pattern.Behavioral.State.Command;

public class Client {

    public static void main(String[] args) {

        Light light = new Light();

        Command turnOnCommand = new TurnOnCommand(light);
        Command turnOffCommand = new TurnOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOnCommand);
        remote.pressButton();
        remote.setCommand(turnOffCommand);
        remote.pressButton();

        
    }

}
