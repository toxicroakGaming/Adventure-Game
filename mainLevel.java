import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class mainLevel {
	private static Player character;
	private static ArrayList<String> astrix;
	private static HashMap<String, Integer> shopItems = new HashMap<String, Integer>();
	private static HashMap<String, Integer> damageShop = new HashMap<String, Integer>();
	private static JFrame frame;
	private static JLabel enemyHP = new JLabel();
	private static JLabel hp_label = new JLabel();
	public static void main(String[] args){
		setFrame();
		shopItems.put("sword", 5);
		damageShop.put("sword", 1);
		shopItems.put("healing potion", 5);
		damageShop.put("healing potion", 1);
		shopItems.put("slasher (horde item)", 20);
		damageShop.put("slasher", 4);
		shopItems.put("spike ball", 20);
		damageShop.put("spike ball", 10);
		shopItems.put("LEGENDARY fire wand", 50);
		damageShop.put("fire wand", 20);
		shopItems.put("RARE sleep spell", 25);
		astrix = new ArrayList<String>();
		astrix.add("                   *");
		astrix.add("                  *");
		// for the player hp tab
		astrix.add("                 *");
		astrix.add("                *");
		astrix.add("               *");
		character = new Player(50, 1);
		hp_label.setText("HP: " + Integer.toString(character.getHP()));
		frame.revalidate();
    	frame.repaint();
		int max = character.getHP();
		character.addWeapon("sword", 1);
		while (character.getHP() > 0) {
			character.addChoice();
			Enemy chosen = pickLevel(character.getEnemies());
			if (chosen.getType().equals("shop")) {
				shop();
			} else if (chosen.getType().equals("rest")) {
				rest(max);
			} else if (chosen.getType().equals("diverge")) {
				System.out.println("PATH DIVERGES");
				diverge();
			} else if (chosen.getType().equals("random diverge")) {

			} else {
				fight(1, chosen);
			}

		}
		System.out.println("GAME OVER");
	}
	
	public static JFrame setFrame(){
		frame = new JFrame("RPG Creation");
		frame.setLayout(new BorderLayout());
		// Load your image (replace with your own path)
		ImageIcon icon = new ImageIcon("pokemon.jpg");
		Image image = icon.getImage();
		BackgroundPanel subPanel1 = new BackgroundPanel(image);
		// Add your components *on top* of the background panel
		subPanel1.setLayout(null); // or any layout you prefe
		frame.setSize(400,400);
		subPanel1.setPreferredSize (new Dimension(3000, 3000));
		ImageIcon hero = new ImageIcon("hero.png");
		ImageIcon slime = new ImageIcon("slom.png");
		Image heroImage = getScaledImage(hero.getImage(), 50, 50);
		Image slimeImage = getScaledImage(slime.getImage(), 50, 50);
		hero = new ImageIcon(heroImage);
		slime = new ImageIcon(slimeImage);
		JLabel slimeLabel = new JLabel("", slime, SwingConstants.LEFT);
		JLabel heroLabel = new JLabel("", hero, SwingConstants.RIGHT);
		slimeLabel.setBounds(220, 200,50, 50);
		heroLabel.setBounds(20, 200, 50, 50);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setContentPane(subPanel1);
		enemyHP = new JLabel();
	    enemyHP.setBounds(220, 310, 80, 25); // (x, y, width, height)
		hp_label = new JLabel();
		hp_label.setBounds(20, 310, 80, 25);
		subPanel1.add(heroLabel);
	    subPanel1.add(slimeLabel);
		frame.add(hp_label);
		frame.add(enemyHP);
		frame.setVisible(true);
	    return frame;
	}


	
	private static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	// full documentation on how the game works such as scenarios and leveling can be found here:
	// https://docs.google.com/document/d/1wbaXPGqkesYlEuxGQqvzEm3kQRkFukUDq4AuaR59qSQ/edit?usp=sharing
	private static Enemy pickLevel(ArrayList<String> choices) {
		// for now, we will just have the default be fight
		Random r = new Random();
		int enemy = r.nextInt(choices.size());
		String choice = choices.get(enemy);
		// what level will it be? this is the first implementation
		// level can range from 1-3 for these enemies
		int level = r.nextInt(3) + 1;
		int gold = r.nextInt(5) + 1;
		Enemy chosen = new Enemy(5 * level, level, 2 * level, level, choice, gold);
		// if the event is a non enemy event, there is a 25% chance it will happen
		if (chosen.getType() == "diverge" || chosen.getType() == "shop"
					|| chosen.getType() == "rest") {
			enemy = r.nextInt(4);
			if (enemy != 2) {
				pickLevel(choices);
			}
		}
		return chosen;
	}

	private static Enemy diverge() {
		System.out.println("Pick 1 to go forward");
		System.out.println("Pick 2 to go left");
		System.out.println("Pick 3 to go right");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		if ((Integer.parseInt(choice) < 4) || (Integer.parseInt(choice) > 0)) {
			if (choice == "1") {
				choice = "forward";
			} else if (choice == "2") {
				choice = "left";
			} else {
				choice = "right";
			}
			System.out.println("You chose to go " + choice);
			return pickLevel(character.getEnemies());
		}
		System.out.println("Invalid option. choose again");
		diverge();
		return null;
	}

	// shop has been chosen
	public static void shop() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(" SHOP ");
		System.out.println(" Anything you already own will double the damage ");
		System.out.println("this feature will be worked on in the near future ");
		Random r = new Random();
		HashMap<String, Integer> curShop = new HashMap<String, Integer>();
		for (int o = 0; o < 4; o++) {
			int choice = r.nextInt(shopItems.size());
			int i = 0;
			for (String s : shopItems.keySet()) {
				if (i == choice) {
					curShop.put(s, shopItems.get(s));
				}
				i++;
			}
		}
		int index = 0;
		for (String s : curShop.keySet()) {
			System.out.println(index + " for " + s);
			System.out.println("cost: " + shopItems.get(s));
			if (s.equals("healing potion")) {
				System.out.println("healing: 5");
			} else {
				System.out.println("damage: " + damageShop.get(s));
			}
			index++;
		}
		System.out.println(index + " to exit");
		System.out.println();
		System.out.println("you have " + character.getGold() + " gold");
		while (true) {
			Scanner sc = new Scanner(System.in);
			String chose = sc.nextLine();
			if (index == Integer.parseInt(chose)) {
				return;
			}
			ArrayList<String> indices = new ArrayList<String>();
			for (int i = 0; i < 4; i++) {
				indices.add(Integer.toString(i));
			}
			while (!(indices.contains(chose))) {
				System.out.println("invalid choice, choose again");
				chose = sc.nextLine();
				if (index == Integer.parseInt(chose)) {
					return;
				}
			}
			int chosen = Integer.parseInt(chose);
			int p = 0;
			for (String s : curShop.keySet()) {
				if (p == chosen) {
					if (shopItems.get(s) <= character.getGold()) {
						character.addWeapon(s, damageShop.get(s));
						System.out.println(
									"You bought " + s + " for " + shopItems.get(s) + " gold");
						character.setGold(character.getGold() - shopItems.get(s));
						System.out.println("You now have " + character.getGold() + " gold");
						character.addWeapon(s, damageShop.get(s));
					}
				}
			}
			System.out.println("what will you choose next?");
		}
	}

	// rest. recover 20 HP
	public static void rest(int max) {
		System.out.println("****************************************");
		System.out.println("*                REST                  *");
		System.out.println("*        you recovered 20 HP!          *");
		System.out.println("****************************************");
		if (character.getHP() + 20 > max) {
			character.setHP(max);
		} else {
			character.setHP(character.getHP() + 20);
		}
		hp_label.setText("HP: " + Integer.toString(character.getHP()));
		frame.revalidate();
    	frame.repaint();

	}

	// this is the main loop for when a fight starts
	// numEnemies is for when theres hordes, can be set to 1 if there is only one enemy
	private static void fight(int numEnemies, Enemy enemy) {
		ArrayList<String> choices = character.getChoices();
		ArrayList<String> validOptions = new ArrayList<String>();
		for (int i = 0; i < choices.size(); i++) {
			validOptions.add("" + i);
		}
		int turn = 0;
		while (enemy.getHP() > 0 && character.getHP() > 0) {
			hp_label.setText("HP: " + Integer.toString(character.getHP()));
			frame.revalidate();
			frame.repaint();
			enemyHP.setText("HP: " + Integer.toString(enemy.getHP()));
			frame.setVisible(true);
			startFight(turn, enemy);
			boolean chosen = false;
			String choice = "";
			while (!chosen) {
				Scanner sc = new Scanner(System.in);
				choice = sc.nextLine();
				if (validOptions.contains(choice)) {
					chosen = true;
					int ind = Integer.parseInt(choice);
					choice = choices.get(ind);
				} else {
					System.out.println("you did not choose a valid choice. Try again!");
				}
			}
			System.out.println();
			System.out.println("You chose " + choice);
			if (choice == "healing potion") {
				character.heal();
			} else {
				Random r = new Random();
				// CRIT CHECK. 1/25 chance to crit
				int crit = r.nextInt(25);
				boolean extraDamage = crit == 2;
				int damage = extraDamage == false ? character.getDamage(choice)
							: character.getDamage(choice) * 2;
				System.out.println("You dealt " + damage + " damage to the " + enemy.getType());
				enemy.setHP(enemy.getHP() - damage);
				if (extraDamage == true) {
					System.out.println("CRIT!");
				}
			}
			System.out.println(enemy.getType() + " dealt " + enemy.getDamage());
			character.setHP(character.getHP() - enemy.getDamage());
			turn++;
		}
		if (enemy.getHP() <= 0) {
			System.out.flush();
			System.out.println("* YOU WIN *");
			System.out.println("You got:");
			System.out.println(enemy.getXP() + " XP");
			character.setXP(character.getXP() + enemy.getXP());
			character.levelUp();
			character.setGold(character.getGold() + enemy.getGold());
			System.out.println(enemy.getGold() + " gold");
		}
		// you died =(
		else {
			System.out.println("* YOU DIED *");
			System.out.println("* YOU LOST " + (character.getGold() / 2) + " gold *");
			character.setGold(character.getGold() / 2);
		}
	}

	private static void startFight(int turn, Enemy enemy) {
		System.out.println("*****************************************");
		if (turn == 0) {
			System.out.print("*      A wild " + enemy.getType()
						+ " has appeared");
			int numSpaces = 41 - (15 + enemy.getType().length() + 13);
			while (numSpaces > 0) {
				System.out.print(" ");
				numSpaces--;
			}
			System.out.println("*");
			System.out.println(
						"*              level " + enemy.getLevel() + "                  *");
		} else {
			int before = 18 - (enemy.getType().length() / 2);
			if (enemy.getType().length() % 2 == 0) {
				before += 1;
			}
			System.out.print("*");
			while (before > 0) {
				System.out.print(" ");
				before--;
			}
			System.out.print(enemy.getType());
			before = 38 - (18 + (enemy.getType().length() / 2));
			while (before > 0) {
				System.out.print(" ");
				before--;
			}
			System.out.println("*");
		}
		String lead = "";
		if (enemy.getHP() < 10) {
			lead = astrix.get(0);
		} else {
			lead = astrix.get(1);
		}
		System.out.println("*                hp " + enemy.getHP() + lead);
		System.out.println("*                                       *");
		if (character.getHP() > 99) {
			lead = astrix.get(4);
		} else if (character.getHP() > 9) {
			lead = astrix.get(3);
		} else {
			lead = astrix.get(2);
		}
		System.out.println("*             YOUR HP " + character.getHP() + lead);
		System.out.println("*          What will you do?            *");
		ArrayList<String> choices = character.getChoices();
		for (int i = 0; i < choices.size(); i++) {
			System.out.print("*             " + i + " for " + choices.get(i));
			int o = 41 - (14 + (Integer.toString(i).length()) + 5 + choices.get(i).length() + 1);
			while (o > 0) {
				System.out.print(" ");
				o--;
			}
			System.out.println("*");
			System.out.print("*             " + character.getDamage(choices.get(i)) + " damage");
			o = 41 - (14 + (Integer.toString(character.getDamage(choices.get(i))).length()) + 8);
			while (o > 0) {
				System.out.print(" ");
				o--;
			}
			System.out.println("*");
			System.out.println("*                                       *");
		}
		System.out.println("*****************************************");
	}

}

