public class Enemy {
	private int hp;
	private int level;
	//the total XP the enemy can give
	private int xp;
	private int damage;
	private String type;
	private int gold;
	public Enemy(int hp, int level, int xp, int damage, String type, int gold) {
		this.hp = hp;
		this.level = level;
		this.xp = xp;
		this.damage = damage;
		this.type = type;
		this.gold = gold;
	}
	public int getGold() {
		return gold;
	}
	public int getHP() {
		return hp;
	}
	public int getDamage() {
		return damage;
	}
	public int setHP(int newHP) {
		hp = newHP;
		return hp;
	}
	public int getLevel() {
		return level;
	}
	public int getXP() {
		return xp;
	}
	public String getType() {
		return type;
	}
}
