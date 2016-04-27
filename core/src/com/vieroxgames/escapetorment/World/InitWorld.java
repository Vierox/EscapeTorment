package com.vieroxgames.escapetorment.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vieroxgames.escapetorment.EscapeTorment;
import com.vieroxgames.escapetorment.References;

import static com.vieroxgames.escapetorment.References.PPM;

/**
 * Created by Ethan on 4/25/2016.
 */
public class InitWorld
{
    private EscapeTorment game;

    private Pixmap pixmap;
    private Texture texture;
    private Sprite ground;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Body player, platform;

    public InitWorld(EscapeTorment game)
    {
        this.game = game;

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        //gamePort = new FitViewport(References.V_WIDTH, References.V_HEIGHT, gameCam);

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        player = createBox(8, 10, 32, 32, false);
        platform = createBox(0, 0, 64, 32, true);
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

        inputUpdate(delta);
        cameraUpdate(delta);
    }

    public void render()
    {
        b2dr.render(world, gameCam.combined.scl(PPM));

        ground = defineSprite();

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        ground.setPosition(0, 0);
        //ground.draw(game.batch);
        game.batch.end();
    }


    public void resize(int width, int height)
    {
        gameCam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
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

    public void inputUpdate(float delta)
    {
        int horizontalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            horizontalForce -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            horizontalForce += 1;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            player.applyForceToCenter(0, 300, false);
        }
        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
    }

    public void cameraUpdate(float delta)
    {
        Vector3 position = gameCam.position;
        position.x = player.getPosition().x * PPM;
        position.y = player.getPosition().y * PPM;
        gameCam.position.set(position);

        gameCam.update();
    }

    public Body createBox(int x, int y, int width, int height, boolean isStatic)
    {
        Body pBody;
        BodyDef def = new BodyDef();

        if (isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

}