import PropTypes from "prop-types";
import React from "react";
import "./Switch.css";

export const SwitchBody = ({ property1, property2, className }) => {
  return (
    <div className={`switch-body ${property1} ${property2} ${className}`}>
      {(property1 === "orange" || property2 === "light" || (property1 === "sky" && property2 === "dark")) && (
        <img
          className="star"
          alt="Star"
          src={
            property2 === "dark"
              ? "star-1.svg"
              : property2 === "on"
              ? "vector-16.svg"
              : property2 === "off"
              ? "vector-16-2.svg"
              : property1 === "land"
              ? "subtract.svg"
              : property1 === "sky" && property2 === "light"
              ? "union.svg"
              : undefined
          }
        />
      )}

      {(property1 === "orange" || property2 === "light" || (property1 === "sky" && property2 === "dark")) && (
        <img
          className="union"
          alt="Union"
          src={
            property2 === "dark"
              ? "star-5.svg"
              : property2 === "on"
              ? "vector-17.svg"
              : property2 === "off"
              ? "vector-17-2.svg"
              : property1 === "land"
              ? "subtract-2.svg"
              : "image.svg"
          }
        />
      )}

      {(property1 === "sky" || property2 === "light") && (
        <img
          className="img"
          alt="Union"
          src={
            property1 === "sky" && property2 === "light"
              ? "union-2.svg"
              : property2 === "dark"
              ? "star-2.svg"
              : property1 === "land"
              ? "subtract-3.svg"
              : undefined
          }
        />
      )}

      {["land", "sky"].includes(property1) &&
        (property1 === "land" || property2 === "dark") &&
        (property1 === "sky" || property2 === "light") &&
        ["light", "dark"].includes(property2) && (
          <img className="subtract" alt="Subtract" src={property2 === "dark" ? "star-3.svg" : "subtract-4.svg"} />
        )}

      {property2 === "dark" && property1 === "sky" && <img className="star-2" alt="Star" src="star-4.svg" />}

      {property2 === "dark" && property1 === "land" && (
        <>
          <div className="ellipse" />
          <div className="div" />
          <div className="ellipse-2" />
          <div className="ellipse-3" />
          <div className="ellipse-4" />
          <div className="ellipse-5" />
          <div className="ellipse-6" />
          <div className="ellipse-7" />
          <div className="ellipse-8" />
          <div className="ellipse-9" />
          <div className="ellipse-10" />
          <div className="ellipse-11" />
          <div className="ellipse-12" />
          <div className="ellipse-13" />
          <div className="ellipse-14" />
        </>
      )}
    </div>
  );
};

SwitchBody.propTypes = {
  property1: PropTypes.oneOf(["three-d", "off", "on", "sky", "orange", "land"]),
  property2: PropTypes.oneOf(["off", "on", "light", "dark", "flat"]),
};
