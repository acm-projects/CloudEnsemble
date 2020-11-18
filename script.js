console.clear();
  var cloud = document.createElement("IMG");
  cloud.setAttribute("src", "cloud-ensemble-logo.png");
  cloud.setAttribute("width", "120");
  cloud.setAttribute("height", "130");
  cloud.style = "position: absolute; top:0px; left:15px;";
  document.body.appendChild(cloud);
  var publish = document.createElement("BUTTON");
  publish.innerText = "Publish";
  publish.style = 'position:absolute; font-size: 20px; top: 30px; right: 50px; margin-left: 10px; color: white; padding: 6px 20px; border: $border-width solid transparent; background-clip: padding-box; transition: .5s all; background-image: linear-gradient(45deg, rgb(11, 45, 126) 0%, rgb(11, 45, 126) 44%,rgb(21, 87, 153) 44%, rgb(21, 87, 153) 45%,rgb(30, 129, 181) 45%, rgb(30, 129, 181) 61%,rgb(40, 170, 208) 61%, rgb(40, 170, 208) 67%,rgb(49, 212, 235) 67%, rgb(49, 212, 235) 100%);  border-radius: 50px; margin-top: 10px; margin-bottom: 10px';
  document.body.appendChild(publish);
const AudioContext = window.AudioContext || window.webkitAudioContext;
const audioCtx = new AudioContext();

const play = document.getElementById('start');

// for convolver
async function createReverb(url) {
    var convolver = audioCtx.createConvolver();
    var response = await fetch(url);
    var arraybuffer = await response.arrayBuffer();
    convolver.buffer = await audioCtx.decodeAudioData(arraybuffer);
    return convolver;
}

var clipObjects = [];
var reverbSettings = [
  {name: 'Beach', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/beach.wav'},
  {name: 'Cave', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/cave.wav'},
  {name: 'Glacier', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/glacier.wav'},
  {name: 'Library', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/library.wav'},
  {name: 'Racquetball Court', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/racquetballcourt.wav'},
  {name: 'Stairwell', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/stairwell.wav'},
  {name: 'Workshop', url: 'http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/audio/workshop.wav'}
];

function selectClip(clipObject) {
  clipObjects.forEach(function(currentObject) {
    currentObject.selectButton.setAttribute('class', 'unchecked');
    currentObject.controlsDiv.classList.add("hidden");
  })
  clipObject.selectButton.setAttribute('class', 'checked');
  clipObject.controlsDiv.classList.remove("hidden");
}

const clips = document.querySelectorAll('audio');

// create each clip object
clips.forEach(function(currentClip, currentIndex) {
  var clipObject = {};
  var impulseURL;

  var clipDiv = document.createElement("DIV");
  var image = document.createElement("IMG");
  var random = Math.floor(Math.random() * 3);
  if(random == 0)
  {
  image.setAttribute("src", "UT-AlbumArt3.png");}
  if(random == 1)
  {
  image.setAttribute("src", "UT-AlbumArt2.png");}
  if(random == 2)
  {
  image.setAttribute("src", "UT-AlbumArt1.png");}
  image.setAttribute("width", "200");
  image.setAttribute("height", "150");
  image.setAttribute("alt", "Album Image");
  image.setAttribute("class", "grid-container");
  image.style = "position:relative; top:150px; left 150px; border-radius: 20px; margin-left: 100px; display:grid;";
  document.body.appendChild(image);
  var addButton = document.createElement("BUTTON");
  addButton.innerText = "Add clip " + (currentIndex+1) + " to rack";
  addButton.setAttribute("class", "add-button");


  var selectButton = document.createElement("BUTTON");
  selectButton.innerText = "Select clip " + (currentIndex+1);
  selectButton.setAttribute("class", "unchecked");
  selectButton.setAttribute("class", "hidden");
  var playButton = document.createElement("BUTTON");
  var testClip;
  var testSource;
  playButton.innerText = "Play clip " + (currentIndex+1);
  playButton.style = "position:relative; top: 150px;margin-left: 110px; margin-bottom: 10px; background-image: linear-gradient(135deg, rgb(13, 4, 178) 0%, rgb(13, 4, 178) 12.5%,rgb(17, 25, 182) 12.5%, rgb(17, 25, 182) 25%,rgb(20, 46, 185) 25%, rgb(20, 46, 185) 37.5%,rgb(24, 67, 189) 37.5%, rgb(24, 67, 189) 50%,rgb(28, 87, 192) 50%, rgb(28, 87, 192) 62.5%,rgb(32, 108, 196) 62.5%, rgb(32, 108, 196) 75%,rgb(35, 129, 199) 75%, rgb(35, 129, 199) 87.5%,rgb(39, 150, 203) 87.5%, rgb(39, 150, 203) 100%); color:white; border-radius: 30px; font-size:18px;";

  // source node
  var source = audioCtx.createMediaElementSource(currentClip);
  var controlsDiv = document.createElement("DIV");
  controlsDiv.classList.add("hidden");

  // delay node
  var delay = audioCtx.createDelay(179);
  delay.delayTime.value = 0;
  var delayDiv = document.createElement("DIV");
  var delayHeader = document.createElement("H3");
  var delayElement = document.createElement("INPUT");
  var delayP = document.createElement("P");
  delayElement.setAttribute("type", "number");
  delayElement.addEventListener("input", function() {
    delay.delayTime.value = this.value / 1000;
    delayP.innerHTML = this.value / 1000 + " seconds";
  });
  delayHeader.innerHTML = "Delay (milliseconds)";
  delayP.innerHTML = 0.000 + " seconds";
 delayHeader.style = 'position:absolute; top: 350px; left: 500px; margin-bottom: 10px;'
 delayP.style = 'position:absolute; top: 410px; left: 500px; margin-bottom: 10px;'
 delayElement.style = 'position:absolute; top: 400px; left: 500px; margin-bottom: 10px;'

  // gain node
  var gain = audioCtx.createGain();
  var gainDiv = document.createElement("DIV");
  var gainHeader = document.createElement("H3");
  var gainElement = document.createElement("INPUT");
  var gainP = document.createElement("P");
  gainElement.setAttribute("type", "range");
  gainElement.addEventListener("input", function() {
    gain.gain.value = this.value / 100 * 2;
    gainP.innerHTML = this.value;
  });
  gainHeader.innerHTML = "Volume";
  gainP.innerHTML = 50;
   gainHeader.style = 'position:absolute; top: 200px; left: 500px; margin-bottom: 10px;'
 gainP.style = 'position:absolute; top: 250px; left: 500px; margin-bottom: 10px;'
 gainElement.style = 'position:absolute; top: 250px; left: 500px; margin-bottom: 10px;'

  // convolver node
  var convolverDiv = document.createElement("DIV");
  var convolverHeader = document.createElement("H3");
  var convolverType = document.createElement("SELECT");
  var convolver = document.createElement("OPTION");
  convolver.setAttribute("value", "blank");
  convolver.innerHTML = "No reverb setting";
  convolverType.appendChild(convolver);
  reverbSettings.forEach(function(reverbObject) {
    convolver = document.createElement("OPTION");
    convolver.setAttribute("value", reverbObject.name.toLowerCase());
    convolver.innerHTML = reverbObject.name;
    convolverType.appendChild(convolver);
  });
  convolverHeader.innerHTML = "Reverberation";
  convolverHeader.style = 'position:absolute; top: 450px; left: 500px; margin-bottom: 10px;'
  convolverType.style = 'position:absolute; top: 500px; left: 500px; margin-bottom: 10px;'

  // parenthetical early connection that you don't need to worry about
  var convolverNode;
  gain.connect(audioCtx.destination);
  convolverType.addEventListener('input', function() {
    if (convolverType.value !== "blank") {
      reverbSettings.forEach(function(reverbObject) {
        if (convolverType.value === reverbObject.name.toLowerCase()) {
          impulseURL = reverbObject.url;
        }
      });
      convolverNode = createReverb(impulseURL).then(function(CN){
        clipObject.convolverNode = CN;
        gain.connect(CN);
        CN.connect(audioCtx.destination);
      });
    } else {
      console.log("we gotta change some stuff");
      gain.disconnect(convolverNode);
      gain.connect(audioCtx.destination);
    }
  });

  // event listeners
  addButton.addEventListener('click', function() {
    selectClip(clipObject);
    if (addButton.classList.contains("racked-button")) {
      addButton.classList.remove("racked-button");
      currentClip.classList.remove("racked");
    } else {
      addButton.classList.add("racked-button");
      currentClip.classList.add("racked");
    }
  })

  selectButton.addEventListener('click', function() {
      selectClip(clipObject);
  });

  playButton.addEventListener('click', function() {
    if (this.classList.contains('playing')) {
      testClip.pause();
      testSource.disconnect(delay);
      this.classList.remove('playing');
    } else {
      testClip = document.createElement("AUDIO");
      testClip.setAttribute("crossorigin", "anonymous");
      testClip.setAttribute("src", currentClip.src);
      testSource = audioCtx.createMediaElementSource(testClip);
      testSource.connect(delay).connect(gain).connect(audioCtx.destination);
      testClip.play();
      this.classList.add('playing');
    }
  });

  clipObject.div = clipDiv;
  clipObject.addButton = addButton;
  clipObject.selectButton = selectButton;
  clipObject.playButton = playButton;
  clipObject.source = source;
  clipObject.controlsDiv = controlsDiv;
  clipObject.delay = delay;
  clipObject.gain = gain;

  clipObjects.push(clipObject);

  clipObject.source.connect(clipObject.delay);
  clipObject.delay.connect(clipObject.gain);
  // gain is already connected to convolver, which is already connected to destination

  clipObject.div.appendChild(clipObject.addButton);
  //clipObject.div.appendChild(clipObject.selectButton);
  clipObject.div.appendChild(clipObject.playButton);
  document.body.appendChild(clipObject.div);
  delayDiv.appendChild(delayHeader);
  delayDiv.appendChild(delayElement);
  delayDiv.appendChild(delayP);
  controlsDiv.appendChild(delayDiv);
  gainDiv.appendChild(gainHeader);
  gainDiv.appendChild(gainElement);
  gainDiv.appendChild(gainP);
  controlsDiv.appendChild(gainDiv);
  convolverDiv.appendChild(convolverHeader);
  convolverDiv.appendChild(convolverType);
  controlsDiv.appendChild(convolverDiv);
  document.body.appendChild(clipObject.controlsDiv);
})

play.addEventListener('click', function() {
  var clipsOnRack = document.querySelectorAll(".racked");
  clipsOnRack.forEach(function(currentClip) {
    currentClip.play();
  });
});
