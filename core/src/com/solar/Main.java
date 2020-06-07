package com.solar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class Main implements ApplicationListener {

    public PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<>();
    public Array<ModelInstance> instances2 = new Array<>();

    public ModelInstance earthInstance;
    public Environment environment;
    public boolean loading;
    float xMov = 0;

    @Override
    public void create() {
        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new PointLight().set(Color.WHITE, 0f, 0f, 0f, 10f));

        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(10, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0, 107, 19);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        assets = new AssetManager();
        assets.load("sun.g3db", Model.class);
        assets.load("mercury.g3db", Model.class);
        assets.load("venus.g3db", Model.class);
        assets.load("earth.g3db", Model.class);
        assets.load("mars.g3db", Model.class);
        assets.load("jupiter.g3db", Model.class);
        assets.load("saturn.g3db", Model.class);
        assets.load("uranus.g3db", Model.class);
        assets.load("neptune.g3db", Model.class);


        assets.load("space.g3db", Model.class);


        loading = true;
    }

    private void doneLoading() {
        Model sun = assets.get("sun.g3db", Model.class);
        Model mercury = assets.get("mercury.g3db", Model.class);
        Model venus = assets.get("venus.g3db", Model.class);
        Model earth = assets.get("earth.g3db", Model.class);
        Model mars = assets.get("mars.g3db", Model.class);
        Model jupiter = assets.get("jupiter.g3db", Model.class);
        Model saturn = assets.get("saturn.g3db", Model.class);
        Model uranus = assets.get("uranus.g3db", Model.class);
        Model neptune = assets.get("neptune.g3db", Model.class);

        Model space = assets.get("space.g3db", Model.class);


        instances.add(new ModelInstance(sun));
        instances.add(new ModelInstance(mercury));
        instances.add(new ModelInstance(venus));
        instances.add(new ModelInstance(earth));
        instances.add(new ModelInstance(mars));
        instances.add(new ModelInstance(jupiter));
        instances.add(new ModelInstance(saturn));
        instances.add(new ModelInstance(uranus));
        instances.add(new ModelInstance(neptune));

        instances2.add(new ModelInstance(space));

        loading = false;
    }

    @Override
    public void render() {
        if (loading && assets.update()) {
            doneLoading();
            loading = false;

        }


        camController.update();


        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.render(instances2);
        modelBatch.end();

        xMov += Gdx.graphics.getDeltaTime();

        //System.out.println(cam.position);

        for (int i = 0; i < instances.size; i++) {
            if (i == 0) {
                instances.get(i).transform.setToRotation(new Vector3(0, 1, 1), 5 * xMov);

            } else if (i == 1) {
                instances.get(i).transform.setToTranslationAndScaling(1.57f * (float) Math.cos(0.46f * (double) xMov), 1.57f * (float) Math.sin(0.46f * (double) xMov), 0, 0.1f, 0.1f, 0.1f);

            } else if (i == 2) {
                instances.get(i).transform.setToTranslationAndScaling(2.65f * (float) Math.cos(1.22f * (double) xMov), 2.65f * (float) Math.sin(1.22f * (double) xMov), 0, 0.3f, 0.3f, 0.3f);

            } else if (i == 3) {
                instances.get(i).transform.setToTranslationAndScaling(3.06f * (float) Math.cos(2f*(double) xMov), 3.06f * (float) Math.sin(2f*(double) xMov), 0, 0.31f, 0.31f, 0.31f);

            } else if (i == 4) {
                instances.get(i).transform.setToTranslationAndScaling(6.87f * (float) Math.cos(3.0f * (double) xMov), 6.87f * (float) Math.sin(3.0f * (double) xMov), 0, 0.2f, 0.2f, 0.2f);

            } else if (i == 5) {
                instances.get(i).transform.setToTranslationAndScaling(8.57f * (float) Math.cos(0.8f * (double) xMov), 8.57f * (float) Math.sin(0.8f * (double) xMov), 0, 1f, 1f, 1f);

            } else if (i == 6) {
                instances.get(i).transform.setToTranslationAndScaling(10f * (float) Math.cos(0.5f * (double) xMov), 10f * (float) Math.sin(0.5f * (double) xMov), 0, 0.6f, 0.6f, 0.6f);

            } else if (i == 7) {
                instances.get(i).transform.setToTranslationAndScaling(11.5f * (float) Math.cos(0.25f*(double) xMov), 11.5f * (float) Math.sin(0.25f*(double) xMov), 0, 0.35f, 0.35f, 0.35f);

            } else if (i == 8) {
                instances.get(i).transform.setToTranslationAndScaling(13f * (float) Math.cos(0.1f * (double) xMov), 13f * (float) Math.sin(0.1f * (double) xMov), 0, 0.34f, 0.34f, 0.34f);

            }

        }
        for (ModelInstance mod : instances2) {
            mod.transform.setToScaling(8f, 8f, 8f);
        }


    }


    @Override
    public void dispose() {
        modelBatch.dispose();
        assets.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}