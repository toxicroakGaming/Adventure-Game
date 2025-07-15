import java.util.ArrayList;
import java.util.HashMap;

public class Player {
	// level is what level the player is. damageMult is affected by this
	private int level;
	private int xp;
	private ArrayList<String> enemyChoices;
	private int hp;
	// based off weapon choice, give how much damage it does
	private HashMap<String, Integer> damage;
	private ArrayList<String> choices;
	private int gold;
	private float damageMult;
	private int maxHP;

	public Player(int hp, float damageMult) {
		maxHP = 50;
		enemyChoices = new ArrayList<String>();
		choices = new ArrayList<String>();
		damage = new HashMap<String, Integer>();
		damage.put("punch", 1);
		level = 1;
		xp = 0;
		this.hp = hp;
		this.damageMult = damageMult;
	}

	// DEBUGGING
	public Player(int hp, float damageMult, int level, int xp) {
		maxHP = 50;
		enemyChoices = new ArrayList<String>();
		choices = new ArrayList<String>();
		damage = new HashMap<String, Integer>();
		damage.put("punch", 1);
		this.level = level;
		this.xp = xp;
		this.hp = hp;
		this.damageMult = damageMult;
	}

	public void heal() {
		if (damage.containsKey("healing potion")) {
			int i = damage.get("healing potion");
			i = i - 1;
			if (i == 0) {
				damage.remove("healing potion");
			} else {
				damage.remove("healing potion");
				damage.put("healing potion", i);
			}
			System.out.println("healed 5 HP!");
			setHP(hp + 5);
		}
	}

	public void addWeapon(String weapon, int wDamage) {
		if (weapon.equals("healing potion")) {
			int i = 0;
			if (damage.containsKey(weapon)) {
				i = damage.get(weapon);
			}
			damage.put(weapon, i + 1);
			return;
		}
		if (choices.contains(weapon)) {
			wDamage *= 2;
		} else {
			choices.add(weapon);
		}
		damage.remove(weapon);
		damage.put(weapon, wDamage);
	}

	public ArrayList<String> getEnemies() {
		return enemyChoices;
	}

	public int getXP() {
		return xp;
	}

	public int setXP(int newXP) {
		xp += newXP;
		return xp;
	}

	public int getDamage(String weapon) {
		return damage.get(weapon);
	}

	public int getHP() {
		return hp;
	}

	public int setHP(int newHP) {
		hp = newHP;
		return getHP();
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	// upon level up, call this function and will add correct choices
	// max level is 10
	public void addChoice() {
		// level 1 will have 3 choices
		if (level == 1) {
			enemyChoices.add("diverge");
			enemyChoices.add("random diverge");
			enemyChoices.add("rest");
			enemyChoices.add("shop");
			enemyChoices.add("slime");
			enemyChoices.add("mushroom");
			enemyChoices.add("rat");
		}
		// level 2 adds another choice
		if (level == 2) {
			enemyChoices.add("goblin");
		}
		// level 3 nothing
		// level 4 adds 1 choice
		if (level == 4) {
			enemyChoices.add("spider");
			enemyChoices.add("skeleton");
		}
		if (level == 5) {
			enemyChoices.add("boss");
		}
		if (level == 6) {
			enemyChoices.add("wolf");
		}
		if (level == 7) {
			enemyChoices.add("ghost");
		}
		if (level == 8) {
			enemyChoices.add("troll");
		}
		if (level == 9) {
			enemyChoices.add("dragon");
		}
		if (level == 10) {
			enemyChoices.add("demon");
		}
	}

	// look at this later and try to simplify numbers and such
	// this is the thresholds for leveling up
	public void levelUp() {
		int oldLevel = level;
		if (xp < 10) {
			level = 1;
		} else if (xp < 50) {
			level = 2;
		} else if (xp < 100) {
			level = 3;
		} else if (xp < 175) {
			level = 4;
		} else if (xp < 500) {
			level = 5;
		} else if (xp < 1000) {
			level = 6;
		} else if (xp < 1750) {
			level = 7;
		} else if (xp < 5000) {
			level = 8;
		} else if (xp < 10000) {
			level = 9;
		} else {
			level = 10;
		}
		if (oldLevel < level) {
			System.out.println("***********");
			System.out.println("*LEVEL UP!*");
			System.out.println("***********");
			setHP(maxHP + 20);
			maxHP += 20;
			for (String s : damage.keySet()) {
				int i = damage.get(s);
				i *= 2;
				damage.remove(s);
				damage.put(s, i);
			}
		}
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int newGold) {
		gold += newGold;
	}
}
