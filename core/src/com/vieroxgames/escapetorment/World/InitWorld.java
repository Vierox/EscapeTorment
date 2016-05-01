package com.vieroxgames.escapetorment.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vieroxgames.escapetorment.Sprites.Entities.Player;
import com.vieroxgames.escapetorment.Utils.GameCamera;
import com.vieroxgames.escapetorment.Utils.Handler;

import static com.vieroxgames.escapetorment.Utils.References.PPM;

/**
 * Created by Ethan on 4/25/2016.
 */
public class InitWorld
{
    private Handler handler;
    private GameCamera gameCamera;

    private Pixmap pixmap;
    private Texture texture;
    private Sprite ground;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;

    private Body platform;

    public InitWorld(Handler handler)
    {
        this.handler = handler;

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        gameCamera = new GameCamera();

        player = new Player(handler, world);
        new PlatformGenerator(world);
    }

    public void dispose()
    {
        world.dispose();
        b2dr.dispose();
        texture.dispose();
    }

    public void update(float delta)
    {
        world.step(1 / 60f, 6, 2);

        player.update(delta);
        cameraUpdate(delta);
    }

    public void render()
    {
        b2dr.render(world, gameCamera.getCamera().combined.scl(PPM));

        ground = defineSprite();

        handler.getBatch().setProjectionMatrix(gameCamera.getCamera().combined);

        handler.getBatch().begin();
        ground.setPosition(0, 0);
        //ground.draw(game.batch);
        handler.getBatch().end();
    }


    public void resize(int width, int height)
    {
        gameCamera.getCamera().setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        //gamePort.update(width, height);
    }

    public Sprite defineSprite()
    {
        pixmap = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        texture = new Texture(pixmap);

        pixmap.dispose();

        Sprite ground = new Sprite(texture);

        return ground;
    }



    public void cameraUpdate(float delta)
    {
        Vector3 position = gameCamera.getCamera().position;
        position.x = player.getPlayer().getPosition().x * PPM;
        position.y = player.getPlayer().getPosition().y * PPM;
        gameCamera.getCamera().position.set(position);

        gameCamera.getCamera().update();
    }



    // GETTERS & SETTERS

}