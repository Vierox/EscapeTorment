package com.vieroxgames.escapetorment.Sprites.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vieroxgames.escapetorment.Utils.Handler;
import com.vieroxgames.escapetorment.World.InitWorld;

import static com.vieroxgames.escapetorment.Utils.References.PPM;

/**
 * Created by Ethan on 4/25/2016.
 */
public class Player
extends Sprite
{
    private Handler handler;
    private InitWorld initWorld;

    private World world;
    private Body player;

    public Player(Handler handler, World world)
    {
        this.handler = handler;

        this.world = world;

        player = createPlayerBody();
    }

    public void update(float delta)
    {
        inputUpdate();
    }

    public void inputUpdate()
    {
        float horizontalForce = 0;
        if(handler.getInputManager().m_left)
        {
            horizontalForce = -5f;
        }
        if(handler.getInputManager().m_right)
        {
            horizontalForce = 5f;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getLinearVelocity().y == 0)
        {
            player.applyForceToCenter(0, 1500, false);
        }
        player.setLinearVelocity(horizontalForce, player.getLinearVelocity().y);
    }

    private Body createPlayerBody()
    {
        Body pBody;
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(0 / PPM, 32 / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(31 / 2 / PPM, 31 / 2 / PPM);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

    // GETTERS & SETTERS
    public Body getPlayer()
    {
        return player;
    }

}
