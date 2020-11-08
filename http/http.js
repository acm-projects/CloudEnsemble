var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

const BASE_URL = "http://cloudens-env.eba-aqmxrmcz.us-east-2.elasticbeanstalk.com/";

async function makeHttpRequest(type, request, param_names, param_contents, callback) {
    var httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            callback(this.responseText);
        }
    };
    var params = "";
    var i = 0;
    param_names.forEach(param_name => {
        params += param_name+"="+encodeURIComponent(param_contents[i])+"&";
        i++;
    });
    params = params.substr(0,params.length-1);
    httpreq.open(type,BASE_URL+request+"?"+params, true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httpreq.send();
}

function doGet(request, param_names, param_contents, on_response) {
    function callback(response) {
        console.log(response);
        var obj = JSON.parse(response);
        on_response(obj);
    };
    makeHttpRequest("GET",request,param_names,param_contents, callback);
}

function doPost(request, param_names, param_contents, on_response) {
    function callback(response) {
        console.log(response);
        var obj = JSON.parse(response);
        on_response(obj);
    };
    makeHttpRequest("POST",request,param_names,param_contents, callback);
}

//CREDENTIALS AND ACCOUNT

function login(email,password,on_response) {
    var param_names = ["email","password"];
    var param_contents = [email,password];
    doPost("login",param_names,param_contents,on_response);
}

function register(email,user_name,password,on_response) {
    var param_names = ["email","user_name","password"];
    var param_contents = [email,user_name,password];
    doPost("register",param_names,param_contents,on_response);
}

function getLogin(on_response) {
    var param_names = [];
    var param_contents = [];
    doGet("logged-in",param_names,param_contents,on_response);
}

function verifyAccount(email,code,on_response) {
    var param_names = ["email","code"];
    var param_contents = [email,code];
    doPost("account/verify",param_names,param_contents,on_response);
}

function resetPasswordReq(email,on_response) {
    var param_names = ["email"];
    var param_contents = [email];
    doPost("account/reset-password",param_names,param_contents,on_response);
}

function changeDesc(desc,on_response) {
    var param_names = ["desc"];
    var param_contents = [desc];
    doPost("account/desc/change",param_names,param_contents,on_response);
}

function getDesc(user_name,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet("account/"+user_name+"/desc",param_names,param_contents,on_response);
}

//BAND

function createBand(band_name,on_response) {
    var param_names = ["band_names"];
    var param_contents = [band_name];
    doPost("band/new",param_names,param_contents,on_response);
}

function joinBand(band_id,on_response) {
    var param_names = ["band_id"];
    var param_contents = [band_id];
    doPost("band/join",param_names,param_contents,on_response);
}

function leaveBand(band_id,on_response) {
    var param_names = ["band_id"];
    var param_contents = [band_id];
    doPost("band/leave",param_names,param_contents,on_response);
}

function deleteBand(band_id,on_response) {
    var param_names = ["band_id"];
    var param_contents = [band_id];
    doPost("band/delete",param_names,param_contents,on_response);
}

function kickMember(band_id,band_member,on_response) {
    var param_names = ["band_id","band_member"];
    var param_contents = [band_id,band_member];
    doPost("band/kick",param_names,param_contents,on_response);
}

//CLIP BOARD

function addToClipboard(track_key,clip_key,on_response) {
    var param_names = ["track_key","clip_key"];
    var param_contents = [track_key,clip_key];
    doPost("tracks/clipboards/add",param_names,param_contents,on_response);
}

function getClipboards(track_key,on_response) {
    var param_names = [];
    var param_contents = [];
    doPost("tracks/"+track_key+"/remove",param_names,param_contents,on_response);
}

function removeFromClipboards(track_key,element_key,on_response) {
    var param_names = ["track_key","element_key"];
    var param_contents = [track_key,element_key];
    doPost("tracks/clipboards/remove",param_names,param_contents,on_response);
}

//afterEle of the element after the location we are moving to,
//should be empty if we are moving to the last position
function moveElement(track_key,element_key,after_key,on_response) {
    var param_names = ["track_key","element_key","after_key"];
    var param_contents = [track_key,element_key,after_key];
    doPost("tracks/clipboards/move",param_names,param_contents,on_response);
}

//CLIPS

function getClips(user_name,on_response) {
    var param_names = ["user_name"];
    var param_contents = [user_name];
    doGet(user_name+"/clips",param_names,param_contents,on_response);
}

function deleteClip(clip_key,on_response) {
    var param_names = ["clip_key"];
    var param_contents = [clip_key];
    doPost("clips/delete",param_names,param_contents,on_response);
}

//SEARCH

function searchPreview(query,on_response) {
    var param_names = ["query"];
    var param_contents = [query];
    doGet("search/preview",param_names,param_contents,on_response);
}

function search(query,page_num,on_response) {
    var param_names = ["query"];
    var param_contents = [query];
    doGet("search/"+page_num,param_names,param_contents,on_response);
}

//STARS

function getStars(user_name,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet(user_name+"/stars",param_names,param_contents,on_response);
}

function addStar(object_key,on_response) {
    var param_names = ["object_key"];
    var param_contents = [object_key];
    doPost("stars/add",param_names,param_contents,on_response);
}

function removeStar(object_key,on_response) {
    var param_names = ["object_key"];
    var param_contents = [object_key];
    doPost("stars/remove",param_names,param_contents,on_response);
}

function hasStarred(user_name,object_key,on_response) {
    var param_names = ["user_name","object_key"];
    var param_contents = [user_name,object_key];
    doGet(user_name+"/has-starred",param_names,param_contents,on_response);
}

//TAGS

function getClipTags(clip_key,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet("clips/"+clip_key+"/tags",param_names,param_contents,on_response);
}

function removeTagFromClip(clip_key,tag_id,on_response) {
    var param_names = ["clip_key","tag_id"];
    var param_contents = [clip_key,tag_id];
    doPost("clips/tags/remove",param_names,param_contents,on_response);
}

function addTagToClip(clip_key,tag_id,on_response) {
    var param_names = ["clip_key","tag_id"];
    var param_contents = [clip_key,tag_id];
    doPost("clips/tags/add",param_names,param_contents,on_response);
}

function getTrackTags(track_key,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet("tracks/"+track_key+"/tags",param_names,param_contents,on_response);
}

function removeTagFromTrack(track_key,tag_id,on_response) {
    var param_names = ["track_key","tag_id"];
    var param_contents = [track_key,tag_id];
    doPost("tracks/tags/remove",param_names,param_contents,on_response);
}

function addTagToTrack(track_key,tag_id,on_response) {
    var param_names = ["track_key","tag_id"];
    var param_contents = [track_key,tag_id];
    doPost("tracks/tags/add",param_names,param_contents,on_response);
}

//TRACKS

function newTrack(track_name,on_response) {
    var param_names = ["track_name"];
    var param_contents = [track_name];
    doPost("tracks/add",param_names,param_contents,on_response);
}

function addSample(track_key,clip_key,time,rack,on_response) {
    var param_names = ["track_key","clip_key","time","rack"];
    var param_contents = [track_key,clip_key,time,rack];
    doPost("tracks/samples/add",param_names,param_contents,on_response);
}

function deleteSample(track_key,sample_key,on_response) {
    var param_names = ["track_key","sample_key"];
    var param_contents = [track_key,sample_key];
    doPost("tracks/samples/add",param_names,param_contents,on_response);
}

function moveSample(track_key,sample_key,time,rack,on_response) {
    var param_names = ["track_key","sample_key","time","rack"];
    var param_contents = [track_key,sample_key,time,rack];
    doPost("tracks/samples/move",param_names,param_contents,on_response);
}

function getSamples(track_key,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet("tracks/"+track_key+"/samples",param_names,param_contents,on_response);
}

function getTracks(user_name,on_response) {
    var param_names = [];
    var param_contents = [];
    doGet(user_name+"/tracks",param_names,param_contents,on_response);
}

function getTracks(user_name,on_response) {
    var param_names = [];
    var param_contents = [];
    doPost(user_name+"/tracks",param_names,param_contents,on_response);
}

//access_level
//0 - Only creator can see
//1 - Anyone can see
//2 - Anyone can edit
function setTrackVisibility(track_key,access_level,on_response) {
    var param_names = ["track_key","access_level"];
    var param_contents = [track_key,access_level];
    doPost("tracks/sharing/set-visibility",param_names,param_contents,on_response);
}

//access_level
//1 - Accessor can see
//2 - Accessor can edit
//type of accessor
//0 - user
//1 - band
function shareTrack(track_key,access_level,accessor,accessor_type,on_response) {
    var param_names = ["track_key","access_level","accessor","accessor_type"];
    var param_contents = [track_key,access_level,accessor,accessor_type];
    doPost("tracks/sharing/share",param_names,param_contents,on_response);
}

function main() {
    function do_sample_response(response_obj) {
        console.log(response_obj.samples[0].sample_key);
        console.log(response_obj.samples[0].clip_key);
        console.log(response_obj.samples[0].time);
        console.log(response_obj.samples[0].rack);
    }
    function do_login_response(response_obj) {
        console.log(response_obj.status);
    }
    function do_search_response(response_obj) {
        for(var i = 0; i < response_obj.result.length; i++) {
            console.log("name: " + response_obj.result[i].name);
            console.log("id: " + response_obj.result[i].id);
            console.log("type: " + response_obj.result[i].type);
        }
    }
    getSamples("5668ef6d-15ba-11eb-a250-435e36ae024923",do_sample_response);
    //search("hello",1,do_search_response);
    //login("ltprichard2014@gmail.com","hello",do_login_response);
}

if (require.main === module) {
    main();
}