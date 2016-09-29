package pl.sk.planets.gameobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.JsonValue;

public class GameObject {
	private Body body;
	private Vector2 scale;

	public Body getBody() {
		return body;
	}

	//public GameObject(World world, JsonValue root, String bodyType, Vector2 position) {
	//	scale= new Vector2(3,1);
	//	createBody(world, bodyType, position);
	//	readBody(root);
	//}

	public GameObject(Builder builder) {
		scale= new Vector2(1,1);
		World world = builder.world;
		JsonValue root = builder.root;
		String bodyType = builder.bodyType;
		Vector2 position = builder.position;
		boolean isPlanet = builder.isPlanet;
		scale = builder.scale;
		createBody(world, bodyType, position);
		if(isPlanet){
			setCircleShapetoBody();
		}else{
			readBody(root);			
		}
	}

	private void setCircleShapetoBody() {
		CircleShape circle = new CircleShape();
		circle.setRadius(3.0f);
		body.createFixture(circle, 0.0f);
	}

	private void createBody(World world, String bodyType, Vector2 position) {
		BodyDef def = new BodyDef();
		if (bodyType == "static") {
			def.type = BodyDef.BodyType.StaticBody;
		}
		if (bodyType == "kinematic") {
			def.type = BodyDef.BodyType.KinematicBody;
		}
		if (bodyType == "dynamic") {
			def.type = BodyDef.BodyType.DynamicBody;
		}
		def.position.set(position);
		def.fixedRotation = true;
		body = world.createBody(def);

	}

	public static class Builder
	{
		private final World world;
		private JsonValue root;
		private String bodyType;
		private Vector2 position;
		private Vector2 scale;
		private boolean isPlanet;
		public Builder (final World world){
			this.world=world;
		}
		public Builder root(final JsonValue root){
			this.root=root;
			return this;
		}
		public Builder bodyType(final String bodyType){
			this.bodyType=bodyType;
			return this;
		}
		public Builder position(final Vector2 position){
			this.position=position;
			return this;
		}
		public Builder position(final float x,final float y){
			this.position=new Vector2(x,y);
			return this;
		}
		public Builder scale(final Vector2 scale){
			this.scale=scale;
			return this;
		}
		public Builder isPlanet(final boolean isPlanet){
			this.isPlanet = isPlanet;
			return this;
		}
		public GameObject build(){
			return new GameObject(this);
		}
	}
	
	public void readBody(JsonValue root) {
		
		JsonValue rigBody = get1StRigBody(root);
		while (rigBody != null) {
			addPolygonsToBody(get1StPolygon(rigBody));
			addCirclesToBody(get1StCircle(rigBody));
			rigBody = rigBody.next();
		}
	}
	
	private void addCirclesToBody(JsonValue firstCircle) {
		JsonValue circle = firstCircle;
		while (circle != null) {
			CircleShape circleShape = new CircleShape();
			circleShape.setPosition(new Vector2(circle.getFloat("cx")*scale.x, circle.getFloat("cy")*scale.y));
			circleShape.setRadius(circle.getFloat("r")*((scale.x+scale.y)/2.0f));
			body.createFixture(circleShape,2.5f);
			circleShape.dispose();
			circle = circle.next();
		}
	}

	private void addPolygonsToBody(JsonValue firstPolygon) {
		JsonValue polygon =firstPolygon;
		while (polygon != null) {
			addPolygonToBody(polygon);
			polygon = polygon.next();
		}
		
	}

	private void addPolygonToBody(JsonValue polygon) {
		PolygonShape ps = new PolygonShape();
		Vector2[] bar =  readVertex(get1StVertex(polygon));
		ps.set(bar);
		body.createFixture(ps,2.5f);
		ps.dispose();	
	}

	private JsonValue get1StVertex(JsonValue polygon) {
		return  polygon.child();
	}
	private Vector2[] readVertex(JsonValue vertex) {
		List<Vector2> vertexList = new ArrayList<Vector2>();
		while (vertex != null) {
			vertexList.add(new Vector2(vertex.getFloat("x")*scale.x, vertex.getFloat("y")*scale.y));
			vertex = vertex.next();
		}
		Vector2[] tempArray = vertexList.toArray(new Vector2[vertexList.size()]);
		return tempArray;
	}
	private JsonValue get1StRigBody(JsonValue root) {
		return root.get("rigidBodies").child();
	}
	private JsonValue get1StPolygon(JsonValue rigBody) {
		return rigBody.get("polygons").child();
	}
	private JsonValue get1StCircle(JsonValue rigBody) {
		return rigBody.get("circles").child();
	}
}
