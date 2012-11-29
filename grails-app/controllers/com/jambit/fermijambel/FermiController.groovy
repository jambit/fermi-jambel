package com.jambit.fermijambel

import org.springframework.context.ApplicationContext;

import com.jambit.jambel.light.LightMode;
import com.jambit.jambel.light.SignalLight
import com.jambit.jambel.light.SignalLightStatus;

import groovy.json.JsonSlurper;

class FermiController {

	SignalLight signalLight
	
	def index() {
		redirect(action: "question", params: params)
	}

	def question() {
		def json = new JsonSlurper().parse(new FileReader("questions.json"))

		[questions: json.questions.collect { questionBlock -> questionBlock.question }]
	}
	
	def answer(int questionChoice, int answer) {
		def json = new JsonSlurper().parse(new FileReader("questions.json"))
		
		def okRange = Eval.me(json.questions[questionChoice].ok) 
		def goodRange = Eval.me(json.questions[questionChoice].good)
		
		if(goodRange.contains(answer)) {
			signalLight.setNewStatus(SignalLightStatus.onlyGreen(LightMode.ON))
			render "${questionChoice}: ${answer}, good!"
		}
		else if(okRange.contains(answer)) {
			signalLight.setNewStatus(SignalLightStatus.onlyYellow(LightMode.ON))
			render "${questionChoice}: ${answer}, ok!"
		}
		else {
			signalLight.setNewStatus(SignalLightStatus.onlyRed(LightMode.ON))
			render "${questionChoice}: ${answer}, bad!"
		}
	}
}
