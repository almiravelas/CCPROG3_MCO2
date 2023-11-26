public class GameLoop{

    private GameGUI gameGUI;
    private Inventory playerInventory;

    public GameLoop(GameGUI gameGUI, Inventory playerInventory){
        this.gameGUI = gameGUI;
        this.playerInventory = playerInventory;
    }

    private void handleViewInventory(){
        InventoryPanel inventoryPanel = new InventoryPanel(playerInventory, this);
        gameGUI.showPanel(inventoryPanel);
    }

    private void handleChangeActiveCreature(){
        ChangeCreaturePanel changeCreaturePanel = new ChangeCreaturePanel(playerInventory, this);
        gameGUI.showPanel(changeCreaturePanel);
    }

    public void handleExploreArea(int areaChoice){
        switch (areaChoice) {
            case 1:
                exploreArea(1, 5, "EL1.txt", this);
                break;
            case 2:
                exploreArea(3, 3, "EL2.txt", this);
                break;
            case 3:
                exploreArea(4, 4, "EL3.txt",this);
                break;
            case 4:
                returnToMainMenu();
                break;
        }
    }

    public void returnToMainMenu() {
        gameGUI.showMainMenu();
    }

}
