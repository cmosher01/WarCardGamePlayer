# War Card Game Player

Copyright © 2020-2021, Christopher Alan Mosher, Shelton, Connecticut, USA, <cmosher01@gmail.com>.

[![License](https://img.shields.io/github/license/cmosher01/WarCardGamePlayer.svg)](https://www.gnu.org/licenses/gpl.html)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=CVSSQ2BWDCKQ2)

Java program that plays a game of War (the card game for two players).

Players are named X and Y.
They use a standard deck of playing cards, 4 suits (which are ignored),
containing cards ranked 1 through 13 (1 being low and 13 being high).

Standard rules of War apply. For a "war", we define that each player
plays three cards face down, followed by one card face up. This rule
also applies to "double" wars (and triple wars, etc.).

We also define rules for unspecified conditions:

* If a player doesn't have enough cards remaining to complete
a full war, then the war uses only as many cards (from each
player, equally) as the losing player is able to play.

* If a player's final card results in a war, a "draw" is declared,
and each player retrieves their card from this war, and returns
it back to their deck.
If there are additional cards engaged in the war, then
the players first swap the sets of engaged cards, then return them
to their decks.

Example output of a game:

    ROUND# : X=cards(                 players' standings                  )cards=Y :  battle   --> winner, or War[size]  cards...
    ------ : --------------------------------------------------------------------- : --------- --> -------------------------------------------- --> -
         1 : X=[26]=(                          =                          )=[26]=Y : X>07|08<Y --> Y
         2 : X=[25]=(                          |*                         )=[27]=Y : X>01|02<Y --> Y
         3 : X=[24]=(                          | *                        )=[28]=Y : X>10|08<Y --> X
         4 : X=[25]=(                          |*                         )=[27]=Y : X>11|01<Y --> X
         5 : X=[26]=(                          =                          )=[26]=Y : X>10|04<Y --> X
         6 : X=[27]=(                         *|                          )=[25]=Y : X>03|03<Y --> W[3] X>13-13<Y X>05-02<Y X>10-07<Y X>04|09<Y --> Y
         7 : X=[22]=(                          |   *                      )=[30]=Y : X>05|04<Y --> X
         8 : X=[23]=(                          |  *                       )=[29]=Y : X>07|02<Y --> X
         9 : X=[24]=(                          | *                        )=[28]=Y : X>11|10<Y --> X
        10 : X=[25]=(                          |*                         )=[27]=Y : X>11|09<Y --> X
        11 : X=[26]=(                          =                          )=[26]=Y : X>03|09<Y --> Y
        12 : X=[25]=(                          |*                         )=[27]=Y : X>05|06<Y --> Y
        13 : X=[24]=(                          | *                        )=[28]=Y : X>09|01<Y --> X
        14 : X=[25]=(                          |*                         )=[27]=Y : X>06|12<Y --> Y
        15 : X=[24]=(                          | *                        )=[28]=Y : X>04|11<Y --> Y
        16 : X=[23]=(                          |  *                       )=[29]=Y : X>07|12<Y --> Y
        17 : X=[22]=(                          |   *                      )=[30]=Y : X>12|05<Y --> X
        18 : X=[23]=(                          |  *                       )=[29]=Y : X>13|08<Y --> X
        19 : X=[24]=(                          | *                        )=[28]=Y : X>01|08<Y --> Y
        20 : X=[23]=(                          |  *                       )=[29]=Y : X>13|06<Y --> X
        21 : X=[24]=(                          | *                        )=[28]=Y : X>03|02<Y --> X
        22 : X=[25]=(                          |*                         )=[27]=Y : X>06|12<Y --> Y
        23 : X=[24]=(                          | *                        )=[28]=Y : X>08|07<Y --> X
        24 : X=[25]=(                          |*                         )=[27]=Y : X>10|08<Y --> X
        25 : X=[26]=(                          =                          )=[26]=Y : X>01|02<Y --> Y
        26 : X=[25]=(                          |*                         )=[27]=Y : X>11|01<Y --> X
        27 : X=[26]=(                          =                          )=[26]=Y : X>10|10<Y --> W[3] X>04-05<Y X>05-13<Y X>04-13<Y X>07|03<Y --> X
        28 : X=[31]=(                     *    |                          )=[21]=Y : X>02|03<Y --> Y
        29 : X=[30]=(                      *   |                          )=[22]=Y : X>11|04<Y --> X
        30 : X=[31]=(                     *    |                          )=[21]=Y : X>10|09<Y --> X
        31 : X=[32]=(                    *     |                          )=[20]=Y : X>09|07<Y --> X
        32 : X=[33]=(                   *      |                          )=[19]=Y : X>11|02<Y --> X
        33 : X=[34]=(                  *       |                          )=[18]=Y : X>01|09<Y --> Y
        34 : X=[33]=(                   *      |                          )=[19]=Y : X>09|03<Y --> X
        35 : X=[34]=(                  *       |                          )=[18]=Y : X>12|06<Y --> X
        36 : X=[35]=(                 *        |                          )=[17]=Y : X>05|05<Y --> W[3] X>13-06<Y X>08-12<Y X>13-11<Y X>06|04<Y --> X
        37 : X=[40]=(            *             |                          )=[12]=Y : X>03|12<Y --> Y
        38 : X=[39]=(             *            |                          )=[13]=Y : X>02|07<Y --> Y
        39 : X=[38]=(              *           |                          )=[14]=Y : X>07|01<Y --> X
        40 : X=[39]=(             *            |                          )=[13]=Y : X>08|08<Y --> W[3] X>10-06<Y X>08-12<Y X>11-02<Y X>01|01<Y --> W[3] X>13-02<Y X>10-03<Y X>05-09<Y X>04|01<Y --> X
        41 : X=[48]=(    *                     |                          )=[04]=Y : X>05|12<Y --> Y
        42 : X=[47]=(     *                    |                          )=[05]=Y : X>10|03<Y --> X
        43 : X=[48]=(    *                     |                          )=[04]=Y : X>04|07<Y --> Y
        44 : X=[47]=(     *                    |                          )=[05]=Y : X>03|02<Y --> X
        45 : X=[48]=(    *                     |                          )=[04]=Y : X>07|12<Y --> Y
        46 : X=[47]=(     *                    |                          )=[05]=Y : X>13|05<Y --> X
        47 : X=[48]=(    *                     |                          )=[04]=Y : X>04|07<Y --> Y
        48 : X=[47]=(     *                    |                          )=[05]=Y : X>11|04<Y --> X
        49 : X=[48]=(    *                     |                          )=[04]=Y : X>09|12<Y --> Y
        50 : X=[47]=(     *                    |                          )=[05]=Y : X>10|07<Y --> X
        51 : X=[48]=(    *                     |                          )=[04]=Y : X>07|07<Y --> W[2] X>09-04<Y X>02-12<Y X>11|09<Y --> X
    WINNER : X=[52]=(*                         |                          )=[00]=Y : X!        --> X
