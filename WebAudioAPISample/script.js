console.clear();

const AudioContext = window.AudioContext || window.webkitAudioContext;
const audioCtx = new AudioContext();

const play = document.getElementById('start');
const test = document.getElementById("test");
const volumeElement = document.getElementById('volume-range');

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
/*var reverbSettings = [
  {name: 'Beach', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/beach.wav'},
  {name: 'Cave', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/cave.wav'},
  {name: 'Glacier', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/glacier.wav'},
  {name: 'Library', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/library.wav'},
  {name: 'Racquetball Court', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/racquetballcourt.wav'},
  {name: 'Stairwell', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/stairwell.wav'},
  {name: 'Workshop', url: 'https://cloud-ensemble.s3.us-east-2.amazonaws.com/IRLib/workshop.wav'}
];*/

const trackGain = audioCtx.createGain();

const clips = document.querySelectorAll('audio');

// create each clip object
clips.forEach(function(currentClip, currentIndex) {
  var clipObject = {};
  var impulseURL;

  var clipDiv = document.createElement("DIV");

  var addButton = document.createElement("BUTTON");
  addButton.innerText = "Add clip " + (currentIndex+1) + " to rack";
  addButton.setAttribute("class", "add-button");

  var selectButton = document.createElement("BUTTON");
  selectButton.innerText = "Select clip " + (currentIndex+1);
  selectButton.setAttribute("class", "unchecked");

  // source node
  var source = audioCtx.createMediaElementSource(currentClip);

  // delay node
  var delay = audioCtx.createDelay(179);
  delay.delayTime.value = 0;
  var delayDiv = document.createElement("DIV");
  var delayHeader = document.createElement("H3");
  var delayElement = document.createElement("INPUT");
  var delayP = document.createElement("P");
  delayDiv.classList.add("hidden");
  delayElement.setAttribute("type", "number");
  delayElement.addEventListener("input", function() {
    delay.delayTime.value = this.value / 1000;
    delayP.innerHTML = this.value / 1000 + " seconds";
  });
  delayHeader.innerHTML = "Delay (milliseconds)";
  delayP.innerHTML = 0.000 + " seconds";

  // gain node
  var gain = audioCtx.createGain();
  var gainDiv = document.createElement("DIV");
  var gainHeader = document.createElement("H3");
  var gainElement = document.createElement("INPUT");
  var gainP = document.createElement("P");
  gainDiv.classList.add("hidden");
  gainElement.setAttribute("type", "range");
  gainElement.addEventListener("input", function() {
    gain.gain.value = this.value / 100 * 2;
    gainP.innerHTML = this.value;
  });
  gainHeader.innerHTML = "Volume";
  gainP.innerHTML = 50;

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
  convolverDiv.classList.add("hidden");

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
    // deselect all clips; select current one
    clipObjects.forEach(function(currentObject) {
      currentObject.selectButton.setAttribute('class', 'unchecked');
    })
    clipObjects[currentIndex].selectButton.setAttribute('class', 'checked');

    if (addButton.classList.contains("racked-button")) {
      addButton.classList.remove("racked-button");
      currentClip.classList.remove("racked");

      // remove controls
      delayDiv.classList.add("hidden");
      gainDiv.classList.add("hidden");
      convolverDiv.classList.add("hidden");
    } else {
      addButton.classList.add("racked-button");
      currentClip.classList.add("racked");

      // show controls
      delayDiv.classList.remove("hidden");
      gainDiv.classList.remove("hidden");
      convolverDiv.classList.remove("hidden");
    }
  })

  selectButton.addEventListener('click', function() {
      clipObjects.forEach(function(currentObject) {
        currentObject.selectButton.setAttribute('class', 'unchecked');
      })
      selectButton.setAttribute('class', 'checked');
  });

  clipObject.div = clipDiv;
  clipObject.addButton = addButton;
  clipObject.selectButton = selectButton;
  clipObject.source = source;
  clipObject.delay = delay;
  clipObject.delayDiv = delayDiv;
  clipObject.gain = gain;
  clipObject.gainDiv = gainDiv;
  //clipObject.convolverNode = convolverNode;
  clipObject.convolverDiv = convolverDiv;

  clipObjects.push(clipObject);

  clipObject.source.connect(clipObject.delay);
  clipObject.delay.connect(clipObject.gain);
  // gain is already connected to convolver, which is already connected to destination

  clipObject.div.appendChild(clipObject.addButton);
  clipObject.div.appendChild(clipObject.selectButton);
  document.body.appendChild(clipObject.div);
  clipObject.delayDiv.appendChild(delayHeader);
  clipObject.delayDiv.appendChild(delayElement);
  clipObject.delayDiv.appendChild(delayP);
  document.body.appendChild(clipObject.delayDiv);
  clipObject.gainDiv.appendChild(gainHeader);
  clipObject.gainDiv.appendChild(gainElement);
  clipObject.gainDiv.appendChild(gainP);
  document.body.appendChild(clipObject.gainDiv);
  clipObject.convolverDiv.appendChild(convolverHeader);
  clipObject.convolverDiv.appendChild(convolverType);
  document.body.appendChild(clipObject.convolverDiv);
})

volumeElement.addEventListener('input', function() {
  document.getElementById("volumep").innerHTML = this.value;
  trackGain.gain.value = this.value / 100 * 2;
});

play.addEventListener('click', function() {
  var clipsOnRack = document.querySelectorAll(".racked");
  clipsOnRack.forEach(function(currentClip) {
    currentClip.play();
  });
});

test.addEventListener('click', function() {
  var audioElement = document.getElementById('ms');
  var source = audioCtx.createMediaElementSource(audioElement);
  source.connect(trackGain).connect(audioCtx.destination);
  audioElement.play();
});
