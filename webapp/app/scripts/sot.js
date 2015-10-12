// Our Basic Parameters
// Id of the Thing on the Left
var thingId;
// The TLA comparison verb
var verb;
// the legal verb values
var verbs = ['ATA','AHA','AWA','ALA','ACA','AVA'];
var verbHTML = {
    'ATA':["is as", "tall", "as"],
    'AHA': ["weighs", "as much", "as"],
    'AWA': ["is as", "wide", "as"],
    'ALA': ["is as", "long", "as"],
    'ACA': ["costs", "as much", "as"],
    'AVA': ["can", "contain", "up to"],
    'AFVA': ["can", "fill", "up"],
    'ARA': ["is as", "round", "as"]
}
// the not safe for work flag
var nsfw = 0;
// the Qty of the thing on the left to use. Not relly implemented yet. 
var thingQty;
// The Id of the Math on the right hand side
var matchId;
// The flag to move back in the stack by 1.
var back = 0;
// busy flag
var busy = 0;

// the thingIds is passed to the server to prevent duplicate results cick over click
var viewedThingIds = [];
// both matchIds and verbs are in sync with the thingIds, and kept for back / fwd functionality
var viewedMatchIds = [];
var viewedVerbs = [];

// the overrides aloow a single-shot override of search functionality without pinning
// this uses the GET parameters and is mostly used for dropping into a specfic URL
var overrideThing = false;
var overrideMatch = false;
var overrideVerb = false;

// pin functionality allows you to keep the thing and/or verb and/or match the same until the pin is cleared
var pinThing = false;
var pinMatch = false;
var pinComparison = false; 

    
// tId - Thing ID. The ID of the left-half thing
// v - The verb in use, see @verbs for the 
// nsfw - A 1 if you are including nsfw results
// thingQty - the quantity of the left-side Thing
// mId - The ID of the matched Thing
function updateStack(tId,v,nsfw,thingQty,mId,back) {
    // process overrides & pins
    if (!pinThing && !overrideThing) { tId = thingId = 0; }
    if (!pinMatch && !overrideMatch) { mId = matchId = 0; }
    if (!pinComparison && !overrideVerb) { v = verbs[Math.floor(Math.random()*verbs.length)]; }        

    //always clear overrides, they are by definition one-shot. Pins are for multiple calls. 
    overrideVerb = overrideMatch = overrideThing = false;

    // process the back button    
    var viewedMatchIdsVar = "";
    if (viewedMatchIds.length>0) {
        // if we are in back mode, just pop off the top
        if (back==1) {
            viewedMatchIds.pop();
            mId = viewedMatchIds.pop();
            viewedThingIds.pop();
            tId = viewedThingIds.pop();
            viewedVerbs.pop();
            v = viewedVerbs.pop();        
        }

        var max = 3;
        if ( viewedMatchIds.length < 3) {
         max = viewedMatchIds.length;   
        }
        for (var i = 0; i < max; i++) { 
            viewedMatchIdsVar += "&h=" + viewedMatchIds[i];
        }
    } 
    
    // one last check. If for any reason we get here and things are not defined, we need to always choose zero. Better random than undefined. 
    tId = (tId != undefined) ? tId : 0; 
    mId = (mId != undefined) ? mId : 0; 
    v = (v != undefined) ? v : "ATA"; 

    var urlString = "http://www.stacksofthings.com/stack/fill?x="+tId+"&v="+v+"&nsfw="+nsfw+"&m="+mId+"&q="+thingQty+viewedMatchIdsVar; 

    // the ajax call
    $.ajax({
        type:"GET",
        url: urlString,
        success: function(result){
            // take care of the local IDs
            thingId = result.thing.id;
            matchId = result.random.id;
            
            // update the url field
            $("#getUrl").val("http://www.stacksofthings.com/?x="+thingId+"&v="+v+"&nsfw="+nsfw+"&m="+matchId);

            // add this one to the stack
            viewedMatchIds.push(result.random.id);
            viewedThingIds.push(thingId);
            viewedVerbs.push(v);

            
            // make sure there are no buttons if there is no back string
            if (viewedMatchIds.length>1){
                $("#backer").css("visibility", "visible");  
            } else {
                $("#backer").css("visibility", "hidden");              
            }
            
            
            // we should only keep 10 results
            if (viewedThingIds.length>10) { 
                viewedMatchIds.shift();
                viewedThingIds.shift();
                viewedVerbs.shift();
            }
            verb = v;

            // Use the corrrect Thing Name, singular or plural
            if (parseFloat(result.thingQty) > 1) {
                $("#thingTitle").text(result.thing.pluralName);
            } else {
                $("#thingTitle").text(result.thing.name);
            }


            // The two images
            $("#thingImageContainer").empty();
            $("#thingImageContainer").append("<img id='thing_img' class='thing_img' src='"+result.thing.imageUrl+"' border='0'/> ");
            $("#matchImageContainer").empty();
            $("#matchImageContainer").append("<img id='thing_img' class='thing_img' src='"+result.random.imageUrl+"' border='0'/> ");
            // clear out and replace the comparison image
            var html = '<div class="compText"><span class="compT compT1">' + verbHTML[verb][0] + '</span><span class="compT compT2">' + verbHTML[verb][1].toUpperCase() + '</span><span class="compT compT3">' + verbHTML[verb][2] + '...</span></div>'
            $("#comparison").html(html);    

            // todo - try some funky transition here
            //$("#comparison").flip(true);
//            $("#comparison").fadeOut('slow', function(){
//                    
//                thisIn.fadeIn('slow', callback); 
//            });
            
            // Match
            if (parseFloat(result.matchQtyNumeric) > 1) {
                $("#matchTitle").text(result.random.pluralName);
            } else {
                $("#matchTitle").text(result.random.name);
            }
            if (result.thingQty == "1") {
                $("#thingQty").html("One");
            } else {
                $("#thingQty").html(result.thingQty);
            }
            $("#matchQty").html(result.matchQty);


            // the units & description
            switch(verb) {
                // As Tall As    
                case "ATA":
                    $("#thingInfo").text(scaleLinearQty(result.thing.height,"Tall"));
                    $("#matchInfo").text(scaleLinearQty(result.random.height,"Tall"));
                    break;
                // As Wide As. 
                case "AWA":
                    $("#thingInfo").text(scaleLinearQty(result.thing.width,"Wide"));
                    $("#matchInfo").text(scaleLinearQty(result.random.width,"Wide"));
                    break;
                // As Long As
                case "ALA":
                    $("#thingInfo").text(scaleLinearQty(result.thing.length,"Long"));
                    $("#matchInfo").text(scaleLinearQty(result.random.length,"Long"));
                    break;
                // As Heavy As
                case "AHA":
                    $("#thingInfo").text(scaleWeightQty(result.thing.weight));
                    $("#matchInfo").text(scaleWeightQty(result.random.weight));
                    break;
                // As Costly As
                case "ACA":
                    $("#thingInfo").text("$"+getFriendlyNumber(result.thing.currentPrice));
                    $("#matchInfo").text("$"+getFriendlyNumber(result.random.currentPrice));
                    break;
                // As Radius As? I guess it can be As Round As, since you can get the cicumference from the Radius      
                case "ARA":
                    $("#thingInfo").text(scaleLinearQty(result.thing.radius,"Radius"));
                    $("#matchInfo").text(scaleLinearQty(result.random.radius,"Radius"));
                    break;
                // As Vluminous As
                case "AVA":
                    $("#thingInfo").html(scaleVolumeQty(result.thing.volume)+" in<sup>3</sup>");
                    $("#matchInfo").html(scaleVolumeQty(result.random.volume)+" in<sup>3</sup>");
                    break;
                // Pick Price as default?
                default:
                    $("#thingInfo").text("?"+getFriendlyNumber(result.thing.currentPrice,verb));
                    $("#matchInfo").text("?"+getFriendlyNumber(result.random.currentPrice,verb));
                    break;
            }
        },
        error: function(xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
            alert(xhr.status+" : "+err);
        } 
    });
    
};
  
function resetButtons() {
    setTimeout( function () {
    $("#spinner").prop("disabled",false);
    $('#spinner').removeClass("spinnerClicked");
    $("#swapper").prop("disabled",false);
    $('#swapper').removeClass("swapperClicked");
    $("#backer").prop("disabled",false);
    $('#backer').removeClass("backerClicked");    
    }, 10);
}

    
/*
    Choose the right unit for inches/ft/miles
*/
function scaleLinearQty(number,dimension) {
    var returnNumber;
    if (number < 1) {
        returnNumber = number + " in.";    
    } else {
        if (number >= 126720.0) {
            // give it in miles    
            returnNumber = $.number(roundVal(parseFloat( number / 63360))) + " mi.";
        } else if (number >= 36.0) {
            // give it in feet
            // if we are under 10 ft, add inches separately
            if (number < 240.00 && ((number % 12) > 0)) {
                var inch = number % 12;
                returnNumber = $.number(roundVal((number - inch)/12)) + " ft, " + Math.round(inch) + " in.";                
            } else {
                var num = number/12;
                returnNumber = $.number(roundVal((number)/12)) + " ft.";
            }
        } else {            
            returnNumber = $.number(number) + " in.";    
        }
    }
    return returnNumber + " " + dimension;
};

/* just give the formatted number, no adaptation */
function getFriendlyNumber(number) {
    var returnNumber;
    if (number < 1) {
        returnNumber = number;    
    } else {
        returnNumber = $.number(number);    
    }
    return returnNumber;
};

/*
    Choose the right unit for inches/ft/miles
*/
function scaleWeightQty(number) {
    var returnNumber;
    if (number < 1) {
        returnNumber = number + " oz.";   
    } else {
        if (number >= 48000.0) {
            // give it in tons    
            returnNumber = $.number(roundVal(parseFloat( number / 32000))) + " tons"
        } else if (number >= 32.0) {
            // give it in pounds
            // if we are under 20 lbs, add ounces separately
            if (number < 320.00 && ((number % 16)) > 0) {
                var ounce = number % 16;
                returnNumber = $.number(roundVal((number - ounce)/12)) + " lbs., " + Math.round(ounce) + " oz.";                
            } else {
                var num = number/16;
                returnNumber = $.number(roundVal((number)/16)) + " lbs.";
            }
        } else {            
            returnNumber = $.number(number) + " oz.";    
        }
    }
    return returnNumber;
};

/*
    Choose the right unit for inches/ft/miles
*/
function scaleVolumeQty(number) {
    var returnNumber;
    if (number < 1) {
        returnNumber = number;    
    } else {
        returnNumber = $.number(number);    
    }
    return returnNumber;
};

function roundVal(val){
    var dec = 2,rounded = Math.round(val*Math.pow(10, dec))/ Math.pow(10, dec);
    return rounded;
}
    
$(document).ready(function() {  
  
        // get the URL parmeters, these override the random or "pinned" functions
        $.urlParam = function(name){
           var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
           return results[1] || 0;
        }
        // sanitize parameters
        thingId = null;
        if (window.location.href.indexOf("x=")>0) {
            thingId = $.urlParam('x');
            // us the url provided param
            overrideThing = true;
        } else {
            overrideThing = false;
            thingId = 0;
        }

        verb = null;
        if (window.location.href.indexOf("v=")>0) {
            verb = $.urlParam('v');
            overrideVerb = true;
        } else {
            verb = verbs[Math.floor(Math.random()*verbs.length)];
            overrideVerb = false;
        }
    
        if (window.location.href.indexOf("b=")>0) {
            back = $.urlParam('b');
        } else {
            back = 0;
        }
    
        nsfw = null;
        if (window.location.href.indexOf("nsfw=")>0) {
            nsfw = $.urlParam('nsfw');
        } else {
            nsfw = 0;
        }
        if (nsfw==1) {
            $("#nsfw").html("NSFW");        
        } else {
            $("#nsfw").html("SFW");                              
        }

        thingQty = "";
        if (window.location.href.indexOf("q=")>0) {
            thingQty = $.urlParam('q');
        } else {
            thingQty = "1";
        }
        
        matchId = "";

        if (window.location.href.indexOf("m=")>0) {
            matchId = $.urlParam('m');
            // this means we cannot pin the match
            overrideMatch = true;
        } else {
            overrideMatch = false;
            matchId = 0;
        }

    // figure out the pin situation
        
        $("#thingPin").empty();
        if (pinThing == true) {            
            $("#thingPin").attr("title", 'Click to randomize this Thing');
        } else {
            $("#thingPin").attr("title", 'Click to pin this Thing');
        }

        $("#comparisonPin").empty();
        if (pinComparison == true) {            
            $("#comparisonPin").attr("title", 'Click to randomize this Comparison');
        } else {
            $("#comparisonPin").attr("title", 'Click to pin this Comparison');
        }
        $("#matchPin").empty();
        if (pinMatch == true) {            
            $("#matchPin").attr("title", 'Click to randomize this Thing');
        } else {
            $("#matchPin").attr("title", 'Click to pin this Thing');
        }
    
        // update the stack info
        updateStack(thingId,verb,nsfw,thingQty,matchId,0);

         /* The Reload Button event */
        $("#spinner").click(function(){
            if (busy==0) {
                busy = 1;
                $(".spinner").stop();
                $(".spinner").effect( "highlight", {color:"#669966"}, 50 );
                // disable the button for a short time
                $("#spinner").prop("disabled",true);
                $('#spinner').addClass("spinnerClicked");

                // reload
                // do the hide and show around this
                var count = 0;
                $('.thing').slideToggle(200, 'linear', function() {
                    //callback function after animation finished
                    if (count == 0) {
                        updateStack(thingId,verb,nsfw,thingQty,matchId,0);
                        count++;    
                    }
                });                    

                $('.thing').slideToggle(200, 'linear',function() {                    
                    busy = 0;
                    resetButtons();
                });
            }
        });
         
        // handle the key press events
        // left arrow is back
        // enter and right arrow are spinner
        // either up or down arrows is swap
        // 112 - P
        // 91 - [
        // 93 - ]
        $(document).keypress(function(e) {
            var key = e.which;
//            alert("key:"+key);
            // the enter key code
            if(key == 13) {
                $('#spinner').click();
                return false;  
            } else if(key == 59) {
                if (viewedMatchIds.length>1) {
                    $('#backer').click();
                }
                return false;  
            } else if(key == 39) {
                $('#swapper').click();
                return false;  
            } else if(key == 112) {
                $('#thingPin').click();
                return false;  
            } else if(key == 91) {
                $('#comparisonPin').click();
                return false;  
            } else if(key == 93) {
                $('#matchPin').click();
                return false;  
            }
        });
    
        /* the Swap Button event   */ 
        $("#swapper").click(function(){
            if (busy==0) {
                busy = 1;
               $(".swapper").stop();
               $(".swapper").effect( "highlight", {color:"rgba(48, 75, 204, 0.75)"}, 50 );
               // disable the button for a short time
               $("#swapper").prop("disabled",true);
               $('#swapper').addClass("swapperClicked");
               // override verb, match & thing & reload
                var count = 0;
                overrideVerb = overrideMatch = overrideThing = true;

                $('.thing').slideToggle(200, 'linear', function() {
                    //callback function after animation finished
                    if (count == 0) {
                        updateStack(matchId,verb,nsfw,thingQty,thingId,0);
                        count++;    
                    }
                });                    

                $('.thing').slideToggle(200, 'linear',function() {                    
                    busy = 0;
                    resetButtons();
                });
            }
        });         
      
        /* The Back Button */
        $("#backer").click(function(){
            if (busy==0) {
                busy = 1;
                // disable the button for a short time
                $("#backer").prop("disabled",true);
                $('#backer').addClass("backerClicked");
                var count = 0;
                $('.thing').slideToggle(200, 'linear', function() {
                    //callback function after animation finished
                    if (count == 0) {
                        updateStack(matchId,verb,nsfw,thingQty,thingId,1);
                        count++;    
                    }
                });                    

                $('.thing').slideToggle(200, 'linear',function() {                    
                    busy = 0;
                    resetButtons();
                });
            }
        });

         
        $("#thingPin").click(function(){
            pinThing = !pinThing;
            if (pinThing) {
                $("#thingPin").attr("title",'Click to randomize this Thing').toggleClass("active");         
            } else {
                $("#thingPin").attr("title", 'Click to pin this Thing').toggleClass("active");                            
            }
        }); 

        $("#comparisonPin").click(function(){
            pinComparison = !pinComparison;
            if (pinComparison) {
                $("#comparisonPin").attr("title", 'Click to randomize this Comparison').toggleClass("active");
            } else {
                $("#comparisonPin").attr("title", 'Click to pin this Comparison').toggleClass("active");            
            }
        }); 

         $("#matchPin").click(function(){
            pinMatch = !pinMatch;
            if (pinMatch) {
                $("#matchPin").attr("title", 'Click to randomize this Thing').toggleClass("active");        
            } else {
                $("#matchPin").attr("title", 'Click to pin this Thing').toggleClass("active");                            
            }
        }); 

        $("#nsfw").click(function(){
            if (nsfw<1) { nsfw = 1 } else { nsfw = 0 };
            if (nsfw==1) {
                $("#nsfw").html("NSFW").addClass("active");        
            } else {
                $("#nsfw").html("SFW").removeClass("active");                              
            }
        });    
    
});


