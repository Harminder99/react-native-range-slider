import { NativeSyntheticEvent } from 'react-native';

export type RangeSliderChangeEvent = NativeSyntheticEvent<{
  min: number;
  max: number;
  target?: any;
  fromUser?: boolean;
}>;

export interface CommonRangeSliderProps {
  min: number;
  max: number;
  onChange?: (min: number, max: number, fromUser: boolean) => void;
  tintColor?: string;
  tintColorBetweenHandles?: string;
  step?: number;
  handleColor?: string;
  style?: object;
  prefix?: string;
  suffix?: string;
  type?: 'slider' | 'range';
  minDistance?: number;
  hideLabels?: boolean;
  selectedMinimum?: number;
  selectedMaximum?: number;
  handleDiameter?: number;
  labelRotation?: number;
}

export interface IOSOnlyRangeSliderProps {
  handleBorderColor?: string;
  handleBorderWidth?: number;
  minLabelColor?: string;
  maxLabelColor?: string;
  lineHeight?: number;
  minLabelFont?: string;
  maxLabelFont?: string;
  minLabelFontSize?: number;
  maxLabelFontSize?: number;
  labelPadding?: number;
  maxDistance?: number;
  lineBorderWidth?: number;
  lineBorderColor?: string;
}

export interface AndroidOnlyRangeSliderProps {
  leftHandleColor?: string;
  rightHandleColor?: string;
  leftHandlePressedColor?: string;
  rightHandlePressedColor?: string;
  handlePressedColor?: string;
  fixGap?: number;
}

export type RangeSliderProps = CommonRangeSliderProps &
  Partial<IOSOnlyRangeSliderProps> &
  Partial<AndroidOnlyRangeSliderProps>;
