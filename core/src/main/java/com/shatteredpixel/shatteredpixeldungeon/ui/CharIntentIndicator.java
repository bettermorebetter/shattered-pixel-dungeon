/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2026 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.ui.Component;

//shows a mob's incoming attack damage above its sprite while it is about to attack the hero.
//modelled on CharHealthIndicator: a scene element that follows its target and reads its state each frame.
public class CharIntentIndicator extends Component {

	private static final int THREAT_COLOR = 0xFF4444;

	private Mob target;
	private RenderedTextBlock text;
	private String shown = null;

	public CharIntentIndicator( Mob m ){
		target = m;
		GameScene.add( this );
	}

	@Override
	protected void createChildren() {
		text = PixelScene.renderTextBlock( 6 );
		text.hardlight( THREAT_COLOR );
		add( text );
	}

	@Override
	public void update() {
		super.update();

		if (target != null && target.isAlive() && target.isActive()
				&& target.sprite != null && target.sprite.visible
				&& target.isThreateningHero()) {

			int[] dmg = target.damageRange();
			if (dmg != null) {
				String str = dmg[0] + "-" + dmg[1];
				if (!str.equals( shown )) {
					text.text( str );
					shown = str;
				}
				CharSprite sprite = target.sprite;
				text.setPos(
						sprite.x + (sprite.width() - text.width()) / 2f,
						sprite.y - text.height() - 1 );
				PixelScene.align( text );
				text.visible = true;
				return;
			}
		}

		text.visible = false;
	}

	public void target( Mob m ) {
		if (m != null && m.isAlive() && m.isActive()) {
			target = m;
		} else {
			target = null;
		}
	}
}
