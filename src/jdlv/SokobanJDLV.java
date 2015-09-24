package jdlv;

import logic.GeneratorPathPlayer;

public  class SokobanJDLV{

int index= 0 ;
logic.World world;
java.util.List<CarryBoxJDLV> carryBoxes;
java.util.List<BoxJDLV> boxes;
java.util.List<String> playerMovement;
public SokobanJDLV(logic.World world){
 this .world=world;
}
public  void  generaPercorso(){
CellJDLV player=world.getPlayerJDLV();
java.util.List<CellJDLV> goals=world.getGoalsJDLV();
java.util.List<CellJDLV> grounds=world.getGroundJDLV();
java.util.List<Integer> times= new java.util.ArrayList();
playerMovement= new java.util.ArrayList();
GeneratorPathPlayer generatorPathPlayer=world.getGeneratorPathPlayer();
int time=goals.size();
for(int i= 1 ;i<=time;i++)
times.add(i);
boolean solutionFounded= false ;
carryBoxes= new java.util.ArrayList();
java.util.List<CellJDLV> deadlockGrounds=world.getDeadlockGroundJDLV();
while(!solutionFounded)
{
int cont= 0 ;
boxes=world.getBoxJDLV();
carryBoxes.clear();
solutionFounded= true ;

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation coloringModule JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_coloringModule=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_coloringModule.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_coloringModule=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_coloringModule;

	// ---- END - startProgram ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(boxes,"positionBox"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'boxes::positionBox' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(boxes,"positionBox"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(goals,"goal"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'goals::goal' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(goals,"goal"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(player,"player"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'player::player' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(player,"player"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(grounds,"ground"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'grounds::ground' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(grounds,"ground"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(carryBoxes,"carryBox"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'carryBoxes::carryBox' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(carryBoxes,"carryBox"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(deadlockGrounds,"deadlockGround"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'deadlockGrounds::deadlockGround' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(deadlockGrounds,"deadlockGround"), 0));

	// ---- END - addInMapping ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'boxes::positionBox' in module coloringModule.");
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'carryBoxes::carryBox' in module coloringModule.");

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_coloringModule.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(times,"time"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'times::time' in module coloringModule:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(times,"time"), 0));

	// ---- END - addInMapping ---- 

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_coloringModule=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation(it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath());
_JDLV_PROGRAM_coloringModule.addText(_JDLV_PROGRAM_BUFFER_coloringModule.toString());
_JDLV_PROGRAM_coloringModule.getFiles().clear();
_JDLV_PROGRAM_coloringModule.addFile("./src/SokobanSolver2");
_JDLV_PROGRAM_BUFFER_coloringModule.append("");
_JDLV_INVOCATION_coloringModule.setInputProgram(_JDLV_PROGRAM_coloringModule);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_coloringModule=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_coloringModule);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution coloringModule program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_coloringModule.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_coloringModule.getInputProgram().getCompleteText(),0)+'\n'+"Files execution: ./src/SokobanSolver2");
_JDLV_INVOCATION_coloringModule.run();
while(_BUFFERED_HANDLER_coloringModule.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELcoloringModule=_BUFFERED_HANDLER_coloringModule.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(boxes, "positionBox",_temporary_JDLV_MODELcoloringModule,BoxJDLV.class);
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(carryBoxes, "carryBox",_temporary_JDLV_MODELcoloringModule,CarryBoxJDLV.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "boxes " + boxes.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(boxes,0)+ "carryBoxes " + carryBoxes.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(carryBoxes,0));
cont++;
solutionFounded= false ;
playerMovement.clear();
CellJDLV source= new CellJDLV(player.getI(),player.getJ());
java.util.Collections.sort(carryBoxes);
for(int i= 0 ;i<carryBoxes.size();i++)
{
CarryBoxJDLV carryB=carryBoxes.get(i);
CellJDLV target=getTarget(carryB);
if(target.equals(source))
{
playerMovement.add(carryB.getDirection());
System.out.println(cont+ " IF " +playerMovement);
source.setI(carryB.getI());
source.setJ(carryB.getJ());
}
else 
{
java.util.List<CellJDLV> positionBox=getPositionBox(boxes,i+ 1 );
System.out.println(positionBox);
for(CellJDLV box:positionBox)
{
generatorPathPlayer.removeVertex(box);
}
generatorPathPlayer.findPathBetween(world.getWorldGround(source.getI(),source.getJ()),target);
for(CellJDLV box:positionBox)
generatorPathPlayer.addVertex(box);
for(CellJDLV box:positionBox)
world.addEdges(box);
if(!generatorPathPlayer.hasSolution())
{
 break ;
}
generatorPathPlayer.addPlayerMovement(playerMovement);
playerMovement.add(carryB.getDirection());
source.setI(carryB.getI());
source.setJ(carryB.getJ());
System.out.println(cont+ " ELSE " +playerMovement);
}
if(i==carryBoxes.size()- 1 )
solutionFounded= true ;
}
if(solutionFounded)
 break ;
}
if(_JDLV_INVOCATION_coloringModule.haveModel()==false){
System.out.println( "NO solution" );
solutionFounded= false ;
}
if(!_JDLV_INVOCATION_coloringModule.getErrors().isEmpty()){
throw new java.lang.RuntimeException(_JDLV_INVOCATION_coloringModule.getErrors().get(0).getText());
}
}
catch(java.lang.Throwable _JDLV_EXCEPTION_coloringModule){
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logErrorMessage(_JDLV_EXCEPTION_coloringModule.getMessage());
}
_JDLV_PROGRAM_coloringModule.cleanText();

	// ---- END - prepareJDLVCall ---- 
time++;
times.add(time);
}
}
private int getDirection(String dir){
 switch(dir){
 case  "up" :
return  0 ;
 case  "down" :
return  1 ;
 case  "right" :
return  2 ;
 case  "left" :
return  3 ;
 default :
 break ;
}

return - 1 ;
}
public int nextStep(){
if(index<playerMovement.size())
return getDirection(playerMovement.get(index++));
return - 1 ;
}
public int precStep(){
return - 1 ;
}
public  void  setIndex(){
index= 0 ;
}
public int getNumberBoxes(){
return world.getBoxJDLV().size();
}
public CellJDLV getTarget(CarryBoxJDLV box){
int i= 0 , j= 0 ;
 switch(box.getDirection()){
 case  "up" :
j=box.getJ();
i=box.getI()+ 1 ;
 break ;
 case  "down" :
j=box.getJ();
i=box.getI()- 1 ;
 break ;
 case  "right" :
j=box.getJ()- 1 ;
i=box.getI();
 break ;
 case  "left" :
j=box.getJ()+ 1 ;
i=box.getI();
 break ;
 default :
 break ;
}

System.out.println( "target " +box.getI()+ " " +box.getJ()+ "    " +i+ "  " +j);
return world.getWorldGround(i,j);
}
private java.util.List<CellJDLV> getPositionBox(java.util.List<BoxJDLV> boxes,int time){
java.util.List<CellJDLV> boxesToReturn= new java.util.ArrayList();
for(BoxJDLV box:boxes)
if(box.getTime()==time)
boxesToReturn.add(world.getWorldGround(box.getI(),box.getJ()));
return boxesToReturn;
}
}

