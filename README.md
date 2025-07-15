### Adventure Java game
This game was developed by me back in the 2022 timeframe.
I plan to update it soon to be better, but it was developed with 
the intention or practicing data structures and object oriented design.

### This project was made locally  in 2022 and imported into a repository
## on 7/15/2025

#### HERE IS WHAT I HAD PLANNED (will I finish it? I am unsure)

VERSIONS:
DEV - more basic, less of a user interface. This version basically shows the battle system
FIN - the final version, will play more like a Pokemon or an RPG that is more interactable

The objective of the game is to survive as long as you can. There will be rewards the longer you survive

ENEMIES
These are listed from weakest to strongest
Each enemy has a special attack they can use or a special effect
Slime - can freeze you for a round (12.5%) - loses 2 hp if he does so
Mushroom - can poison you causing you to take 1 damage per round the rest of the fight (5%)
rat
Goblin - can steal gold, but if you win, you get double what he stole and then some (12.5%)
spider
skeleton
Boss - he has a 10% chance to steal half your health round 1
Wolf - bite can infect you, causing you to have different attacks (1%)
Ghost - can dodge attacks and cause you to miss (5%)
Troll - play on his terrain. He attacks first and he has a double attack that has a 25% chance to happen every turn
Dragon - might burn you causing 2 damage per turn (2%)
Demon - can haunt you, causing attacks to do 50% damage for 2 turns (5%)
 

EVENTS:
There are a few events that can take place, from most common at the top to least common at the bottom. As the game is built, there may be more or less events than shown here. Any non enemy event has a 10% chance to be chosen. This will only be in the DEV VERSION

Enemy encounters - singles
Enemy encounters - hordes
Shop
Rest - recover 20 HP
Special event

Every 5 fights, there will be a town that comes up where you can pay gold to heal, buy items at a shop, talk to the civilians to gather info and choose your path forward. The shops in towns are the only ones that will give you items to increase strength and give you an advantage.

As well, there will be some times where you can choose between left and right to go to another area. You will ALWAYS either get both ends are known or one is random the other is an enemy encounter

After you visit a certain amount of towns, there will be one named Champions road, where you will be able to fight the final boss. The mechanics are going to be different from a regular encounter, but it is undecided in what ways.

You get gold for killing enemies as well as XP. As of now, the max level is level 10. In the shop, you will have the chance to buy a random assortment of items (tiered). The more expensive an item is, the more effective it is at what it is set out to do. Single target weapons will increase their single target damage, magic items could increase their effectiveness and multi target weapons increase damage across the board. The goal of this game will be to come up with good strategy that will be varied and cause you to think at every fight

ARMOR:
Characters and enemies may wear armor. Certain classes may also only wear certain armor. Here is the total list of armor (strength will be defined later)
None - 1
Leather - 2
Coal - 2 (temp, easy to find and build)
Chain - 3
Copper - 4
Iron - 5
Gold - 6 (low durability, but strong)
Diamond - 7 (high durability, but high cost)

Besides none, each armor will have a durability. Durability describes how long until dropoff/breaking of armor will be. Once your durability is below a certain threshold(20 for high durability, 50 for regular durability, 70 for low durability, 90 for temp) it will be less effective. 
Strength is somewhat exponential
Damage will be negated or completely ignored up to a certain point. Stronger attacks will take more durability points from your total. 
Any set but diamond and leather will burn and melt when a fire attack is used on it
Every armor will start at durability 100


WEAPONS:
The rates for finding weapons are calculated as follows:
There is a random number generator that will pick a number 1-500
Common will be assigned to 300 of those values
Rare will be assigned to 150 of those values
Epic will be assigned to 31 of those values
Legendary will be assigned to 10 of the values
Relic (ultra legendary) will be assigned to 1 value



SPELLS -

Each spell has a damage and a cost

Fire - cost 10, damage 5 (plus burn damage, can vary based on character)
This spell will light a character on fire. Regular burn is 1 damage per turn

Heal - cost 6 damage -5 (heals)

Protect - cost 20 damage none. Protects you fully from damage of an incoming attack

Status heal - cost 5, damage -2 (heals 2) - takes any status effects you may have away

Sleepy potion - cost 3 damage 0 - puts the target to sleep, while asleep, theres a 1/20 chance to wake up, every turn increasing the odds for 5 turns
1/20, 1/10, 1/5, 1/3 , 1/2, 2/3, wakeup. While asleep, the target cant use spells, attack or negate damage. However, any attack that does more than half the target’s  HP will wake them up and cause them to have slow start, causing their next attack to do half the damage

FIGHT PHYSICS
If you choose to use a damage dealing weapon, you will have a 1/25 chance to crit the enemy dealing double the damage you do! 
