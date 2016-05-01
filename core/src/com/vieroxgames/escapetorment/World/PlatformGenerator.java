package com.vieroxgames.escapetorment.World;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vieroxgames.escapetorment.Utils.References;

import java.util.Random;

import static com.vieroxgames.escapetorment.Utils.References.PPM;

/**
 * Created by Ethan on 4/25/2016.
 */
public class PlatformGenerator
{
    private World world;

    private boolean[][] platformMap;

    public PlatformGenerator(World world)
    {
        this.world = world;

        platformMap = new boolean[References.P_WIDTH][References.P_HEIGHT];

        initPlatformGeneration();
    }

    private void initPlatformGeneration()
    {
        float platformChance = 0.7f;

        stepCreate(platformChance);

        step2();

        step3();
    }

    private void stepCreate(float platformChance)
    {
        //STEP 1
        for(int y=0; y<References.P_HEIGHT; y++)
        {
            for(int x=0; x< References.P_WIDTH; x++)
            {
                if(randFloat(0, 1) <= platformChance)
                    platformMap[x][y] = true;
                else
                    platformMap[x][y] = false;
            }
        }
    }

    private void step2()
    {
        int stepNum = 3;
        //STEP 2
        for (int i=0; i<stepNum; i++)
        {
            for (int y = 0; y < References.P_HEIGHT; y++)
            {
                for (int x = 0; x < References.P_WIDTH; x++)
                {
                    if (x > 0 && y > 0 && x < References.P_WIDTH - 1 && y < References.P_HEIGHT - 1 &&
                            !platformMap[x][y] && platformMap[x + 1][y] && platformMap[x - 1][y] &&
                            platformMap[x][y + 1] && platformMap[x][y - 1]) {
                        platformMap[x][y] = true;
                    }

                }
            }
        }
    }

    private void step3()
    {
        for(int y=0; y<References.P_HEIGHT; y++)
        {
            for(int x=0; x< References.P_WIDTH; x++)
            {
                if(platformMap[x][y])
                {
                    createBody(x, y);
                }
            }
        }
    }

    private Body createBody(int x, int y)
    {
        Body pBody;
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.StaticBody;

        def.position.set(x - References.P_WIDTH / 2, y - References.P_HEIGHT);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / 2 / PPM, 16 / 2 / PPM);

        FixtureDef shapeFixture = new FixtureDef();
        shapeFixture.density = 0.0f;
        shapeFixture.shape = shape;
        shapeFixture.restitution = 0f;
        shapeFixture.friction = 0f;

        pBody.createFixture(shapeFixture);
        shape.dispose();

        return pBody;
    }

    private float randFloat(float min, float max)
    {
        Random random = new Random();
        float randFloat = min + (max - min) * random.nextFloat();

        return randFloat;
    }

}
