package com.botscrew.messengercdk.model.outgoing.element.button;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* Class which is used when Jackson deserializer cannot define Button subclass base on "type" property.
* This is the case of ShareButton: for echo events, Facebook doesn't return "type" field in JSON objects of Share Buttons.
* */
@Getter
@Setter
@NoArgsConstructor
public class DefaultButton extends Button {}
